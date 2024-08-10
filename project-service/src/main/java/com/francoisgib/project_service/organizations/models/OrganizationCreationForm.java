package com.francoisgib.project_service.organizations.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreationForm {
	@NotNull
	@NotBlank
	@Size(max = 30)
	private String organizationName;
}
