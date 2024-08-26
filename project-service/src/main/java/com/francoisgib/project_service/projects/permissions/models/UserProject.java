package com.francoisgib.project_service.projects.permissions.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_project")
public class UserProject {
    @EmbeddedId
    private UserProjectId userProjectId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_project_permission",
            joinColumns = {
                    @JoinColumn(name = "project_id"),
                    @JoinColumn(name = "user_id"),
            },
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<ProjectPermission> projectPermissions;
}