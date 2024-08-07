package com.francoisgib.kubernetesservice.services;

import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ServiceService {
    private final CoreV1Api coreV1Api;

    public V1ServiceList getAllServices() throws ApiException {
        return coreV1Api.listServiceForAllNamespaces().execute();
    }

    public V1Service createService() throws ApiException {
        V1ObjectMeta metadata = new V1ObjectMeta()
                .name("service-test");

        V1ServiceSpec spec = new V1ServiceSpec()
                .selector(Map.of("app", "app-organization"))
                .type("ClusterIP")
                .ports(List.of(
                        new V1ServicePort()
                                .protocol("TCP")
                                .port(80)
                                .targetPort(new IntOrString(80))
                ));

        V1Service service = new V1Service()
                .spec(spec)
                .metadata(metadata);

        return coreV1Api.createNamespacedService("organization", service).execute();
    }
}
