package com.francoisgib.kube_service.deployments.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DeploymentDTO {
    private String uid;
    private String name;
    private String namespace;
    private OffsetDateTime creationTimestamp;
    private HashMap<String, String> labels;
    private DeploymentSpec spec;
}
