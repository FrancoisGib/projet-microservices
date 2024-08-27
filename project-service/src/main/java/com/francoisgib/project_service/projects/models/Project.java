package com.francoisgib.project_service.projects.models;

import com.francoisgib.project_service.organizations.models.Organization;
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectScope scope;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
