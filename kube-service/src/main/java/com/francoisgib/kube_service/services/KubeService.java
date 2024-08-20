package com.francoisgib.kube_service.services;

import com.francoisgib.kube_service.KubeResourceCreationForm;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class KubeService extends KubeResourceCreationForm {
    private ServiceType serviceType;
    private Set<ServicePort> ports;
}
