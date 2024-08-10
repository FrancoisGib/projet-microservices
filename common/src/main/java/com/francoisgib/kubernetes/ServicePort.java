package com.francoisgib.kubernetes;

public record ServicePort(
        int port,
        ServicePortType protocol,
        int targetPort
) {

}
