package com.francoisgib.kube_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class KubeResourceCreationForm {
    private String name;
    private String namespace;
    private Map<String, String> labels;
}
