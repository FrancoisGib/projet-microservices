package com.francoisgib.kube_service.services;


import com.francoisgib.kube_service.PortProtocol;

public record ServicePort(
        int port,
        int targetPort,
        PortProtocol protocol
) {
}