package com.francoisgib.kubernetesservice;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Namespace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/global")
public class GlobalController {
    private final GlobalService globalService;

    @PostMapping("/{namespace}")
    public V1Namespace initNamespace(@PathVariable String namespace) throws ApiException {
        return globalService.initNamespace(namespace);
    }

}
