package com.francoisgib.kubernetesservice.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCreationForm {
    @NotNull
    @NotBlank
    private String name;

    private Map<String, String> selectors;

    @NotNull
    private ServiceType type;
}
