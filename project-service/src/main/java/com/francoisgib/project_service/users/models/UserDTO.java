package com.francoisgib.project_service.users.models;

import com.francoisgib.project_service.projects.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private int organizationId;
	private Set<Long> projectsId;
}
