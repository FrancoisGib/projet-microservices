package com.francoisgib.kube_service.deployments.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistryCredentials {
    private String username;
    private String password;
    private String auth;
}