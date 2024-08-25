package com.francoisgib.project_service.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DatabaseLoader implements CommandLineRunner {
    @Override
    public void run(String... args) {

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
