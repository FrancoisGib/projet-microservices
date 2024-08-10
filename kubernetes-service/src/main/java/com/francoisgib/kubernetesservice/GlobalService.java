package com.francoisgib.kubernetesservice;

import com.francoisgib.kubernetes.*;
import com.francoisgib.kubernetesservice.deployments.DeploymentService;
import com.francoisgib.kubernetesservice.namespaces.NamespaceService;
import com.francoisgib.kubernetesservice.services.ServiceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GlobalService {
    private final NamespaceService namespaceService;

    private final DeploymentService deploymentService;

    private final ServiceService serviceService;

    public V1Namespace initNamespace(String namespaceName) throws ApiException {
        namespaceService.createNamespace(namespaceName);

        DeploymentCreationForm deploymentCreationForm = DeploymentCreationForm.builder()
                .namespace(namespaceName)
                .image("registry")
                .labels(Map.of("app", "registry"))
                .name("registry")
                .build();
        deploymentService.createDeployment(deploymentCreationForm);

        ServiceCreationForm serviceCreationForm = ServiceCreationForm.builder()
                .namespace(namespaceName)
                .labels(Map.of("app", "registry"))
                .name("registry")
                .ports(Set.of(new ServicePort(80, ServicePortType.TCP, 5000)))
                .type(ServiceType.ClusterIP)
                .build();
        serviceService.createService(serviceCreationForm);

        return namespaceService.getNamespace(namespaceName);
    }

}
