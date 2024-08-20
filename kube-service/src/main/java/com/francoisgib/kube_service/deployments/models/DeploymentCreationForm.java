package com.francoisgib.kube_service.deployments.models;

import com.francoisgib.kube_service.KubeResourceCreationForm;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeploymentCreationForm extends KubeResourceCreationForm {
    private String image;
    private String username;
    private String password;
    private String registryUrl;
}
