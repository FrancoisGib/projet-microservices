package com.francoisgib.project_service.projects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String organization;
    private String name;
    private String description;
    private Set<Long> usersId;
    private ProjectScope scope;
}