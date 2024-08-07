package com.francoisgib.kubernetesservice.deployments;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DeploymentService {
    private final AppsV1Api kubernetesAppsApi;

    public V1DeploymentList getAllDeployments() throws ApiException {
        return kubernetesAppsApi.listDeploymentForAllNamespaces().execute();
    }

    public V1Deployment createDeployment() throws ApiException {
        V1ObjectMeta deploymentMetadata = new V1ObjectMeta()
                .name("test-deployment")
                .labels(Map.of("app", "app-organization"))
                .namespace("organization");

        V1ObjectMeta podMetadata = new V1ObjectMeta()
                .name("pod-test")
                .labels(Map.of("app", "app-organization"))
                .namespace("organization");

        V1PodSpec podSpec = new V1PodSpec()
                .containers(List.of(new V1Container()
                        .name("app-nginx")
                        .image("nginx:latest")));

        V1DeploymentSpec deploymentSpec = new V1DeploymentSpec()
                .selector(new V1LabelSelector()
                        .matchLabels(Map.of("app", "app-organization")))
                .template(new V1PodTemplateSpec()
                        .spec(podSpec)
                        .metadata(podMetadata)
                );

        V1Deployment deployment = new V1Deployment()
                .metadata(deploymentMetadata)
                .spec(deploymentSpec);

        return kubernetesAppsApi.createNamespacedDeployment(
                "organization",
                deployment).execute();
    }
}
