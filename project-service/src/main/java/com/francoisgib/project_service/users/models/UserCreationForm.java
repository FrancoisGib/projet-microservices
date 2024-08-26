package com.francoisgib.project_service.users.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationForm {
	@NotBlank
	@NotNull
	private String username;

	@NotBlank
	@Email
	@Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a right email")
	private String email;

	@NotBlank
	private String password;

	private Integer organizationId;
}