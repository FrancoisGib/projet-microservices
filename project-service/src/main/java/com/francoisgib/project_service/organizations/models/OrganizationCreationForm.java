package com.francoisgib.project_service.organizations.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreationForm {
	private Long ownerId;

	private String name;
}
