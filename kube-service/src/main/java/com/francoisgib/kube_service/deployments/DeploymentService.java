package com.francoisgib.kube_service.deployments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.francoisgib.kube_service.deployments.models.DeploymentCreationForm;
import com.francoisgib.kube_service.deployments.models.RegistryAuth;
import com.francoisgib.kube_service.deployments.models.RegistryCredentials;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeploymentService {
    private final AppsV1Api appsV1Api;
    private final CoreV1Api coreV1Api;

    public V1Deployment createDeployment(DeploymentCreationForm deploymentCreationForm) throws ApiException, JsonProcessingException {

        String concatUsernamePassword = deploymentCreationForm.getUsername() + ":" + deploymentCreationForm.getPassword();
        String encodedString = Base64.getEncoder().encodeToString(concatUsernamePassword.getBytes());

        RegistryAuth auth = RegistryAuth.builder()
                .auths(Map.of(deploymentCreationForm.getRegistryUrl(),
                        RegistryCredentials.builder()
                                .auth(encodedString)
                                .username(deploymentCreationForm.getUsername())
                                .password(deploymentCreationForm.getPassword()).build()
                ))
                .build();

        String res = new ObjectMapper().writeValueAsString(auth);

        V1Secret secret = new V1Secret()
                .putStringDataItem(".dockerconfigjson", res)
                .type("kubernetes.io/dockerconfigjson")
                .metadata(new V1ObjectMeta()
                        .name(deploymentCreationForm.getRegistryUrl())
                        .namespace(deploymentCreationForm.getNamespace()));

        coreV1Api.createNamespacedSecret(deploymentCreationForm.getNamespace(), secret).execute();

        V1ObjectMeta deploymentMetadata = new V1ObjectMeta()
                .name(deploymentCreationForm.getName())
                .labels(deploymentCreationForm.getLabels())
                .namespace(deploymentCreationForm.getNamespace());

        V1ObjectMeta podMetadata = new V1ObjectMeta()
                .name(deploymentCreationForm.getName())
                .labels(deploymentCreationForm.getLabels())
                .namespace(deploymentCreationForm.getNamespace());

        V1PodSpec podSpec = new V1PodSpec()
                .addImagePullSecretsItem(new V1LocalObjectReference().name(deploymentCreationForm.getRegistryUrl()))
                .containers(List.of(new V1Container()
                        .name(deploymentCreationForm.getName())
                        .image(deploymentCreationForm.getImage())));

        V1DeploymentSpec deploymentSpec = new V1DeploymentSpec()
                .selector(new V1LabelSelector()
                        .matchLabels(deploymentCreationForm.getLabels()))
                .template(new V1PodTemplateSpec()
                        .spec(podSpec)
                        .metadata(podMetadata)
                );

        V1Deployment deployment = new V1Deployment()
                .metadata(deploymentMetadata)
                .spec(deploymentSpec);

        return appsV1Api.createNamespacedDeployment(
                deploymentCreationForm.getNamespace(),
                deployment).execute();
    }

    public List<V1Deployment> getAllDeployments() throws ApiException {
        return appsV1Api.listDeploymentForAllNamespaces().execute().getItems();
    }

    public List<V1Deployment> getDeploymentsByNamespace(String namespace) throws ApiException {
        return appsV1Api.listNamespacedDeployment(namespace).execute().getItems();
    }

    public V1Deployment getNamespaceDeploymentByName(String namespace, String name) throws ApiException {
        return appsV1Api.readNamespacedDeployment(name, namespace).execute();
    }
}