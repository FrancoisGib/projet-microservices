package com.francoisgib.user_service.users;

import com.francoisgib.user_service.users.models.User;
import com.francoisgib.user_service.users.models.UserDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO toDTO(User user);
	List<UserDTO> toDTO(List<User> users);
}
