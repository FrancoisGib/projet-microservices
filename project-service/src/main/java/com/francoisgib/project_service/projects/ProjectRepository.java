package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.projects.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);

    @Query("SELECT p FROM Project p WHERE p.name ILIKE :name%")
    Page<Project> findAllByNameStartingWithLowerName(@Param("name") String name, Pageable pageable);
}
