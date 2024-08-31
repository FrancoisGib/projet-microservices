package com.francoisgib.project_service.projects.permissions;

import com.francoisgib.project_service.projects.permissions.models.UserProject;
import com.francoisgib.project_service.projects.permissions.models.UserProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId> {
}
