package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.PageDTO;
import com.francoisgib.project_service.projects.models.ProjectCreationForm;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.projects.permissions.ProjectPermissionMapper;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermissionDTO;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermissionEnum;
import com.francoisgib.project_service.users.UserResourceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectCreationForm projectCreationForm) throws UserResourceException {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.toDTO(projectService.createProject(projectCreationForm)));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectBydId(@PathVariable Long projectId) {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.toDTO(projectService.getProject(projectId)));
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

    @GetMapping("/permissions/{projectId}/{userId}")
    public Set<ProjectPermissionDTO> getUserProjectPermissions(@PathVariable Long projectId, @PathVariable Long userId) throws UserResourceException {
        return ProjectPermissionMapper.INSTANCE.toDTO(projectService.getUserProjectPermissionsByUserIdAndProjectId(userId, projectId));
    }

    @GetMapping("/permissions/access/{projectId}/{userId}")
    public ResponseEntity<Void> userCanAccessProject(@PathVariable Long projectId, @PathVariable Long userId) throws UserResourceException {
        HttpStatus status = projectService.userHasProjectPermission(userId, projectId, ProjectPermissionEnum.ACCESS) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).build();
    }

    @GetMapping("/users")
    public PageDTO<ProjectDTO> getUserProjectsWithPaginationAndStartsWithName(@RequestParam long userId, @RequestParam String nameFilter, @RequestParam int pageNumber) {
        return ProjectMapper.INSTANCE.toDTO(projectService.getUserProjectsWithPagination(userId, nameFilter, pageNumber));
    }
}
