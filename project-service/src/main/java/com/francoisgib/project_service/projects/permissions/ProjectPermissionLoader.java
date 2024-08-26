package com.francoisgib.project_service.projects.permissions;

import com.francoisgib.project_service.projects.permissions.models.ProjectPermission;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermissionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
@Order(1)
public class ProjectPermissionLoader implements CommandLineRunner {
    private final ProjectPermissionRepository projectPermissionRepository;

    @Override
    public void run(String... args) {
        Set<ProjectPermission> projectPermissions = Arrays.stream(ProjectPermissionEnum.values())
                .map(ProjectPermission::new).collect(Collectors.toSet());
        List<ProjectPermission> alreadyInDatabasePermissions = projectPermissionRepository.findAll();
        projectPermissions = projectPermissions.stream().filter(projectPermission -> !alreadyInDatabasePermissions.contains(projectPermission)).collect(Collectors.toSet());
        if (!projectPermissions.isEmpty()) {
            log.info("Saving permissions to database");
            projectPermissionRepository.saveAll(projectPermissions);
        }
    }

    /*private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private Role userRole;

    private Role adminRole;

    private List<Role> databaseRoles;

    private List<Permission> databasePermissions;

    private final Set<Permission> newPermissions = new HashSet<>();

    private boolean updated = false;

    @Override
    public void run(String... args) {
        databaseRoles = roleRepository.findAll();
        databasePermissions = permissionRepository.findAll();

        userRole = findRoleIfExists("USER");
        adminRole = findRoleIfExists("ADMIN");

        addUserPermissions();
        if (updated) {
            log.info("Roles updated");
            permissionRepository.saveAll(newPermissions);
            roleRepository.saveAll(Set.of(userRole, adminRole));
        }
    }

    public Role findRoleIfExists(String name) {
        return databaseRoles.stream().filter(role -> role.getName().equals(name)).findAny().orElseGet(() -> {
            log.info("Creating new role {}", name);
            return new Role(name);
        });
    }

    public void addAllPermissions(Set<Permission> permissions) {
        for (Permission permission : permissions) {
            if (databasePermissions.stream()
                    .noneMatch(databasePermission -> databasePermission
                            .getName()
                            .equals(permission.getName()))
            ) {
                newPermissions.add(permission);
                for (Role role : permission.getRoles()) {
                    log.info("Adding new permission {} to role {}", permission.getName(), role.getName());
                    role.addPermission(permission);
                }
                updated = true;
            }
        }
    }

    public void addUserPermissions() {
        final Set<Permission> permissions = Set.of(
                new Permission("user:read", Set.of(userRole)),
                new Permission("admin:read", Set.of(adminRole))
        );
        addAllPermissions(permissions);
    }*/
}
