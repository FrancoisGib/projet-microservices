package com.francoisgib.project_service.users.models;

import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.projects.permissions.models.UserProject;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String password;

	@ManyToOne
	private Organization organization;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_project",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
	)
	private Set<Project> projects;
}
