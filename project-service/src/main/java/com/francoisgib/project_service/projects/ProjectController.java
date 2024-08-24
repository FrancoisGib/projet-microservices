package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.projects.models.ProjectCreationForm;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.users.UserResourceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectCreationForm projectCreationForm) throws UserResourceException {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.toDTO(projectService.createProject(projectCreationForm)));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.toDTO(projectService.getAllProjects()));
    }
}
