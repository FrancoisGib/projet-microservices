package com.francoisgib.kube_service.deployments.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeploymentSpec {
    private int replicas;
    private Selector selector;
    private DeploymentTemplate template;
}
