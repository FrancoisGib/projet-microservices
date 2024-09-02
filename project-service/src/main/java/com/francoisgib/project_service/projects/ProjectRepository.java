package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.projects.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p JOIN " +
            "UserProject up ON p.id = up.userProjectId.project.id WHERE up.userProjectId.user.id = :userId" +
            " AND p.name ILIKE :nameFilter%")
    Page<Project> findUserProjectsStartingWithName(long userId, String nameFilter, Pageable pageable);
}
