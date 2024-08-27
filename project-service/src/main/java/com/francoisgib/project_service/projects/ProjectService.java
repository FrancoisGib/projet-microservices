package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.organizations.OrganizationService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.projects.models.ProjectCreationForm;
import com.francoisgib.project_service.projects.permissions.ProjectPermissionRepository;
import com.francoisgib.project_service.projects.permissions.UserProjectRepository;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermission;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermissionEnum;
import com.francoisgib.project_service.projects.permissions.models.UserProject;
import com.francoisgib.project_service.projects.permissions.models.UserProjectId;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    private final OrganizationService organizationService;

    private final UserService userService;

    private final UserProjectRepository userProjectRepository;

    private final ProjectPermissionRepository projectPermissionRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    public Project createProject(ProjectCreationForm projectCreationForm) throws UserResourceException {
        Organization organization = organizationService.getOrganizationById(projectCreationForm.getOrganizationId());
        User user = userService.getUser(projectCreationForm.getUserId());
        Project newProject = Project.builder()
                .users(Set.of(user))
                .organization(organization)
                .scope(projectCreationForm.getScope())
                .name(projectCreationForm.getName())
                .description(projectCreationForm.getDescription())
                .build();

        Project project = projectRepository.save(newProject);
        Set<ProjectPermission> projectPermissions = new HashSet<>(projectPermissionRepository.findAll());
        userProjectRepository.save(new UserProject(new UserProjectId(user, newProject), projectPermissions));
        return project;
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Set<ProjectPermission> getUserProjectPermissionsByUserIdAndProjectId(Long userId, Long projectId) throws UserResourceException {
        User user = userService.getUser(userId);
        Project project = getProject(projectId);
        return userProjectRepository.findById(new UserProjectId(user, project)).orElseThrow().getProjectPermissions();
    }

    public boolean userHasProjectPermission(Long userId, Long projectId, ProjectPermissionEnum projectPermission) throws UserResourceException {
        Set<ProjectPermission> permissions = getUserProjectPermissionsByUserIdAndProjectId(userId, projectId);
        return permissions.stream()
                .anyMatch(permission -> permission.getPermission() == projectPermission);
    }

    public Set<ProjectPermission> getUserPermissionsByUserAndProject(User user, Project project) {
        return userProjectRepository.findById(new UserProjectId(user, project)).orElseThrow().getProjectPermissions();
    }

    public boolean userCanAccessProject(Long userId, Long projectId) throws UserResourceException {
        Project project = getProject(projectId);
        User user = userService.getUser(userId);
        switch (project.getScope()) {
            case PUBLIC -> {
                return true;
            }
            case ORGANIZATION -> {
                return projectOrganizationScopedAndUserInOrganization(
                        project.getOrganization(),
                        user,
                        project);
            }
            case PRIVATE -> {
                return projectPrivateAndUserInProject(project, userId);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean projectOrganizationScopedAndUserInOrganization(Organization organization, User user, Project project) {
        return organization != null && organization.getUsers().stream().anyMatch(organizationUser ->
                organizationUser.getId().equals(user.getId()) &&
                        getUserPermissionsByUserAndProject(user, project).stream()
                                .anyMatch(projectPermission -> projectPermission.getPermission() == ProjectPermissionEnum.ACCESS));
    }

    private boolean projectPrivateAndUserInProject(Project project, Long userId) {
        return project.getUsers().stream().anyMatch(user -> user.getId().equals(userId));
    }
}
