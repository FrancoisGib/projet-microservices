package com.francoisgib.kubernetesservice.services;

import com.francoisgib.kubernetes.ServiceCreationForm;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceService {
    private final CoreV1Api coreV1Api;

    public V1ServiceList getAllServices() throws ApiException {
        return coreV1Api.listServiceForAllNamespaces().execute();
    }

    public V1Service createService(ServiceCreationForm serviceCreationForm) throws ApiException {
        V1ObjectMeta metadata = new V1ObjectMeta()
                .name(serviceCreationForm.getName() + "-service");

        List<V1ServicePort> ports = serviceCreationForm.getPorts()
                .stream().map(port -> new V1ServicePort()
                        .port(port.port())
                        .protocol(port.protocol().toString())
                        .targetPort(new IntOrString(port.targetPort())))
                .toList();

        V1ServiceSpec spec = new V1ServiceSpec()
                .selector(serviceCreationForm.getLabels())
                .type(serviceCreationForm.getType().toString())
                .ports(ports);

        V1Service service = new V1Service()
                .spec(spec)
                .metadata(metadata);

        return coreV1Api.createNamespacedService(serviceCreationForm.getNamespace(), service).execute();
    }
}
