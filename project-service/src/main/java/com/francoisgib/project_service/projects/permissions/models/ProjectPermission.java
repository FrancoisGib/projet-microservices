package com.francoisgib.project_service.projects.permissions.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_permission")
public class ProjectPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ProjectPermissionEnum permission;

    public ProjectPermission(ProjectPermissionEnum projectPermission) {
        this.permission = projectPermission;
    }
}