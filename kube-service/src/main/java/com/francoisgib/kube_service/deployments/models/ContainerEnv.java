package com.francoisgib.kube_service.deployments.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.kubernetes.client.openapi.models.V1EnvVarSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContainerEnv {
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private V1EnvVarSource valueFrom;
}