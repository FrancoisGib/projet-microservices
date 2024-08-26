package com.francoisgib.project_service.users.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO {
	private Long id;
	private String username;
	private Integer organizationId;
	private Set<Long> projectsId;
}
