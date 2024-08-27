package com.francoisgib.project_service.config;

import com.francoisgib.project_service.organizations.OrganizationService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationCreationForm;
import com.francoisgib.project_service.projects.ProjectService;
import com.francoisgib.project_service.projects.models.ProjectCreationForm;
import com.francoisgib.project_service.projects.models.ProjectScope;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(2)
public class DatabaseLoader implements CommandLineRunner {
    private final OrganizationService organizationService;

    private final UserService userService;

    private final ProjectService projectService;

    @Override
    public void run(String... args) throws Exception {
        Organization organization = organizationService.createOrganization(new OrganizationCreationForm("Organization"));

        User user = userService.createUser(new UserCreationForm(
                "username",
                "user@user.com",
                "Password1!",
                organization.getId()));

        for (int i = 0; i < 4; i++) {
            projectService.createProject(new ProjectCreationForm(
                    user.getId(),
                    "Premier projet",
                    ProjectScope.PRIVATE,
                    "Première description du project",
                    organization.getId()));

            projectService.createProject(new ProjectCreationForm(
                    user.getId(),
                    "Deuxième projet",
                    ProjectScope.PRIVATE,
                    "Deuxième description du project",
                    organization.getId()));

            projectService.createProject(new ProjectCreationForm(
                    user.getId(),
                    "Troisième projet",
                    ProjectScope.PRIVATE,
                    "Troisième description du project",
                    organization.getId()));

            projectService.createProject(new ProjectCreationForm(
                    user.getId(),
                    "Quatrième projet",
                    ProjectScope.PRIVATE,
                    "Quatrième description du project",
                    organization.getId()));
        }
    }
}
