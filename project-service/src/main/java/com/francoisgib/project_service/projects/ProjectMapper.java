package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.users.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Override
    @Mapping(target = "usersId", source = "users", qualifiedByName = "mapUsersId")
    @Mapping(target = "organizationId", source = "organization.id")
    ProjectDTO toDTO(Project object);

    @Named("mapUsersId")
    default Set<Long> mapUsersId(Set<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toSet());
    }
}