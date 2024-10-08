package com.francoisgib.project_service.projects.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreationForm {
    @NotNull
    private Long userId;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    private ProjectScope scope;

    @NotNull
    @NotBlank
    private String description;

    private Integer organizationId;
}
