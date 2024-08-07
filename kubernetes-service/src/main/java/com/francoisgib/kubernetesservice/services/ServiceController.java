package com.francoisgib.kubernetesservice.services;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    public List<V1Service> getAllServices() throws ApiException {
        return serviceService.getAllServices().getItems();
    }

    @PostMapping
    public V1Service createService() throws ApiException {
        return serviceService.createService();
    }
}
