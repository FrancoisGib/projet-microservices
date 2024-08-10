package com.francoisgib.project_service.users;

import com.francoisgib.project_service.BaseMapper;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO> {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "organizationId", source = "organization.id")
	UserDTO toDTO(User user);
}
