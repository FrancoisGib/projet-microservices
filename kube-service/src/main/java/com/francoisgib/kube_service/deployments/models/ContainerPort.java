package com.francoisgib.kube_service.deployments.models;

import com.francoisgib.kube_service.PortProtocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContainerPort {
    private int containerPort;
    private PortProtocol protocol;
}
