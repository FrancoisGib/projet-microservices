package com.francoisgib.project_service.organizations;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.organizations.models.OrganizationDTO;
import com.francoisgib.project_service.projects.Project;
import com.francoisgib.project_service.users.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDTO> {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Override
    @Mapping(target = "usersId", source = "users", qualifiedByName = "mapUsersId")
    @Mapping(target = "projectsId", source = "projects", qualifiedByName = "mapProjectsId")
    OrganizationDTO toDTO(Organization object);

    @Named("mapUsersId")
    default Set<Long> mapUsersId(Set<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toSet());
    }

    @Named("mapProjectsId")
    default Set<Long> mapProjectsId(Set<Project> projects) {
        return projects.stream().map(Project::getId).collect(Collectors.toSet());
    }
}