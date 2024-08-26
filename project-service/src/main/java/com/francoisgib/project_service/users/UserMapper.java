package com.francoisgib.project_service.users;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO> {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "organizationId", source = "organization.id")
	@Mapping(target = "projectsId", source = "projects", qualifiedByName = "mapProjectsId")
	UserDTO toDTO(User user);

	@Named("mapProjectsId")
	default Set<Long> mapProjectsId(Set<Project> projects) {
		return projects.stream().map(Project::getId).collect(Collectors.toSet());
	}
}
