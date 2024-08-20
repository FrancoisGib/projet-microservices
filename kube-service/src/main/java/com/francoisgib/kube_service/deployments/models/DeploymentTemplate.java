package com.francoisgib.kube_service.deployments.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeploymentTemplate {
    private String name;
    private HashMap<String, String> labels;
    private List<ContainerEnv> env;
    private String image;
    private List<ContainerPort> ports;
}