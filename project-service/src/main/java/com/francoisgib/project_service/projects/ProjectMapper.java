package com.francoisgib.project_service.projects;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.projects.models.Project;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Override
    @Mapping(target = "organization", source = "organization.name")
    ProjectDTO toDTO(Project object);
}