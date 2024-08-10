package com.francoisgib.kubernetes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class ServiceCreationForm {
    private String name;
    private String namespace;
    private Map<String, String> labels;
    private ServiceType type;
    private Set<ServicePort> ports;
}
