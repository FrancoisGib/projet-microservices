package com.francoisgib.kube_service.deployments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistryAuth {
    private Map<String, RegistryCredentials> auths;
}