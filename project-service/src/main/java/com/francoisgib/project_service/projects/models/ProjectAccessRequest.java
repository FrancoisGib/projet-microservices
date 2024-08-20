package com.francoisgib.project_service.projects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectAccessRequest {
    private String projectName;
    private Long userId;
}
