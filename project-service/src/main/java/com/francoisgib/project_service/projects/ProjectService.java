package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.organizations.OrganizationService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.projects.models.ProjectAccessRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    private final OrganizationService organizationService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(ProjectCreationForm projectCreationForm) {
        Organization organization = organizationService.getOrganizationById(projectCreationForm.getOrganizationId());

        Project newProject = Project.builder()
                .organization(organization)
                .scope(projectCreationForm.getScope())
                .name(projectCreationForm.getName())
                .build();

        return projectRepository.save(newProject);
    }

    public boolean userCanAccessProject(ProjectAccessRequest projectAccessRequest) {
        Project project = projectRepository.findByName(projectAccessRequest.getProjectName());
        switch (project.getScope()) {
            case PUBLIC -> { return true; }
            case ORGANIZATION -> { return projectOrganizationScopedAndUserInOrganization(
                    project.getOrganization(),
                    projectAccessRequest.getUserId());
            }
            case PRIVATE -> { return projectPrivateAndUserInProject(project, projectAccessRequest.getUserId()); }
            default -> { return false; }
        }
    }

    private boolean projectOrganizationScopedAndUserInOrganization(Organization organization, Long userId) {
        return organization.getUsers().stream().anyMatch(user -> user.getId().equals(userId));
    }

    private boolean projectPrivateAndUserInProject(Project project, Long userId) {
        return project.getUsers().stream().anyMatch(user -> user.getId().equals(userId));
    }
}
