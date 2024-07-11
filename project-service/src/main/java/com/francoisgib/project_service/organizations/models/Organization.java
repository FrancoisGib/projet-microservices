package com.francoisgib.project_service.organizations.models;

import com.francoisgib.project_service.users.Role;
import com.francoisgib.project_service.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "organization")
public class Organization {
	@Id
	private String id;

	@Size(max = 30)
	@NotNull
	@NotBlank
	@Indexed(unique = true)
	private String name;

	private Set<User> users;
}
