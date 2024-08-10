package com.francoisgib.kubernetesservice.deployments;

import com.francoisgib.kubernetes.DeploymentCreationForm;
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
    //public List<DeploymentDTO> getAllDeployments() throws ApiException {
        //return DeploymentMapper.INSTANCE.toDTO(deploymentService.getAllDeployments());
    public List<V1Deployment> getAllDeployments() throws ApiException {
        try {
        return deploymentService.getAllDeployments().getItems().stream().toList();
        }
        catch (ApiException e) {
            e.printStackTrace();
            System.err.println(e.getResponseBody());
            System.err.println(e.getCode());
            System.err.println(e.getMessage());
        }
        return null;
    }

    @PostMapping
    public V1Deployment createDeployment(@RequestBody DeploymentCreationForm deploymentCreationForm) throws ApiException {
        return deploymentService.createDeployment(deploymentCreationForm);
    }
}
