package com.francoisgib.kube_service.deployments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.francoisgib.kube_service.deployments.mappers.DeploymentMapper;
import com.francoisgib.kube_service.deployments.models.DeploymentCreationForm;
import com.francoisgib.kube_service.deployments.models.DeploymentDTO;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deployments")
public class DeploymentController {
    private final DeploymentService deploymentService;

    @GetMapping
    public List<V1Deployment> getAllDeployments() throws ApiException {
        return deploymentService.getAllDeployments();
    }

    @GetMapping("/{namespace}")
    public List<V1Deployment> getDeploymentsByNamespace(@PathVariable String namespace) throws ApiException {
        return deploymentService.getDeploymentsByNamespace(namespace);
    }

    @GetMapping("/{namespace}/{name}")
    public DeploymentDTO getNamespaceDeploymentByName(@PathVariable String namespace, @PathVariable String name) throws ApiException {
        return DeploymentMapper.INSTANCE.toDTO(deploymentService.getNamespaceDeploymentByName(namespace, name));
    }

    @PostMapping
    public DeploymentDTO createDeployment(@RequestBody DeploymentCreationForm deploymentCreationForm) throws ApiException, JsonProcessingException {
        return DeploymentMapper.INSTANCE.toDTO(deploymentService.createDeployment(deploymentCreationForm));
    }
}
