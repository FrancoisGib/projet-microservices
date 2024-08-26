package com.francoisgib.project_service.projects.permissions;

import com.francoisgib.project_service.projects.permissions.models.ProjectPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectPermissionRepository extends JpaRepository<ProjectPermission, Integer> {
}
