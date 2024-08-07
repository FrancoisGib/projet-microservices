package com.francoisgib.kubernetesservice.pods;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PodService {
    private final CoreV1Api kubernetesCoreAPI;

    public V1PodList getAllPods() throws ApiException {
        return kubernetesCoreAPI.listPodForAllNamespaces().execute();
    }

    public V1Pod createPod(String podName) throws ApiException {
        V1ObjectMeta metadata = new V1ObjectMeta()
            .name(podName)
            .labels(Map.of("app", "app-organization"));

        V1Container container = new V1Container()
            .name("app-nginx")
            .image("nginx:alpine-slim");

        V1PodSpec podSpec = new V1PodSpec()
                .containers(List.of(container));

        V1Pod pod = new V1Pod()
                .spec(podSpec)
                .metadata(metadata);

        return kubernetesCoreAPI.createNamespacedPod(
                "organization", pod).execute();
    }
}
