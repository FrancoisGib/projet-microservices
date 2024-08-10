package com.francoisgib.project_service.users.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateForm {
	@NotBlank
	private String username;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	private int organizationId;
}
