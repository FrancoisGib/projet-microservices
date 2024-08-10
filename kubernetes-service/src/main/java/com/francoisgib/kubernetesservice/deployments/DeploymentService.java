package com.francoisgib.kubernetesservice.deployments;

import com.francoisgib.kubernetes.DeploymentCreationForm;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeploymentService {
    private final AppsV1Api kubernetesAppsApi;

    public V1DeploymentList getAllDeployments() throws ApiException {
        return kubernetesAppsApi.listDeploymentForAllNamespaces().execute();
    }

    public V1Deployment createDeployment(DeploymentCreationForm deploymentCreationForm) throws ApiException {
        V1ObjectMeta deploymentMetadata = new V1ObjectMeta()
                .name(deploymentCreationForm.getName() + "-deployment")
                .labels(deploymentCreationForm.getLabels())
                .namespace(deploymentCreationForm.getNamespace());

        V1ObjectMeta podMetadata = new V1ObjectMeta()
                .name(deploymentCreationForm.getName())
                .labels(deploymentCreationForm.getLabels())
                .namespace(deploymentCreationForm.getNamespace());

        V1PodSpec podSpec = new V1PodSpec()
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

        return kubernetesAppsApi.createNamespacedDeployment(
                deploymentCreationForm.getNamespace(),
                deployment).execute();
    }
}
