package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.projects.models.ProjectScope;
import com.francoisgib.project_service.users.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ProjectScope scope;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
