package com.francoisgib.project_service.projects.models;

import com.francoisgib.project_service.users.models.UserDTO;
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
    private Set<UserDTO> users;
    private ProjectScope scope;
}