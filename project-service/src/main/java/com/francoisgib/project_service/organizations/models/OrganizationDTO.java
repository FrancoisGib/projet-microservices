package com.francoisgib.project_service.organizations.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class OrganizationDTO {
    private Long id;
    private String name;
    private Set<Long> usersId;
    private Set<Long> projectsId;
}
