package com.francoisgib.user_service.users.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "_user")
public class User {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	@NotNull
	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;

	@NotNull
	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@NotBlank
	@Column(nullable = false, unique = true)
	private String password;

	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String organizationId;
}
