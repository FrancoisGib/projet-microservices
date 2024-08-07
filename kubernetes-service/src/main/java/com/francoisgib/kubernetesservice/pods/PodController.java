package com.francoisgib.kubernetesservice.pods;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Pod;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pods")
public class PodController {
    private final PodService podService;

    @GetMapping
    public List<PodDTO> getPods() throws ApiException {
        return PodMapper.INSTANCE.toDTO(podService.getAllPods());
    }

    @PostMapping
    public V1Pod createPod(@RequestBody PodCreationForm podCreationForm) throws ApiException {
        return podService.createPod(podCreationForm.getName());
    }
}
