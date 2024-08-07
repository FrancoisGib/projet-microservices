package com.francoisgib.kubernetesservice.pods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodCreationForm {
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    String image;
}
