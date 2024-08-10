package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.organizations.OrganizationService;
import com.francoisgib.project_service.organizations.models.Organization;
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
}
