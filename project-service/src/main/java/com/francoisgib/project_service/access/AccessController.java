package com.francoisgib.project_service.access;

import com.francoisgib.project_service.projects.ProjectService;
import com.francoisgib.project_service.projects.models.ProjectAccessRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/access")
public class AccessController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Void> hasUserAccessToProject(@Valid @RequestBody ProjectAccessRequest projectAccessRequest) {
        return projectService.userCanAccessProject(projectAccessRequest) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
    }
}
