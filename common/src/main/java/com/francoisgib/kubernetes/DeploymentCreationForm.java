package com.francoisgib.kubernetes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentCreationForm {
    private String name;
    private String namespace;
    private Map<String, String> labels;
    private String image;
}
