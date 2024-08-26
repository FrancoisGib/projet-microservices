package com.francoisgib.project_service.organizations.models;

import com.francoisgib.project_service.projects.models.Project;
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
@Table(name = "organization")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
	private Set<User> users;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
	private Set<Project> projects;
}
