package com.francoisgib.project_service.projects.permissions;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermission;
import com.francoisgib.project_service.projects.permissions.models.ProjectPermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectPermissionMapper extends BaseMapper<ProjectPermission, ProjectPermissionDTO> {
    ProjectPermissionMapper INSTANCE = Mappers.getMapper(ProjectPermissionMapper.class);
}
