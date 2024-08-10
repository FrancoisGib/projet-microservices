package com.francoisgib.project_service.users.models;

import com.francoisgib.project_service.organizations.models.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private int organizationId;
}
