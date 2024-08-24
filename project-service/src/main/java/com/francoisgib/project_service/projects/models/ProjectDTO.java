package com.francoisgib.project_service.projects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private int organizationId;
    private String name;
    private Set<Long> usersId;
}