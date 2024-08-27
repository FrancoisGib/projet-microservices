package com.francoisgib.project_service.users.controllers;

import com.francoisgib.project_service.ArtificialPage;
import com.francoisgib.project_service.ResponseObject;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.users.UserMapper;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserDTO;
import com.francoisgib.project_service.users.models.UserUpdateForm;
import java.util.List;

import com.francoisgib.project_service.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Component
public class UserControllerImpl implements UserController {
	private final UserService userService;

	@Override
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.getAllUsers()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> getUserByEmail(String email) throws UserResourceException {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.findByEmail(email)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> getUserByUsername(String username) throws UserResourceException {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.findByUsername(username)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> getUserById(long id) throws UserResourceException {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.getUser(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> createUser(UserCreationForm userCreationForm) throws UserResourceException {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.createUser(userCreationForm)), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(@PathVariable long id, UserUpdateForm userUpdateForm) throws UserResourceException {
		return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(userService.updateUser(id, userUpdateForm)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseObject> deleteUser(long id) throws UserResourceException {
		userService.deleteUser(id);
		return new ResponseEntity<>(new ResponseObject("User successfully deleted", HttpStatus.OK.value()), HttpStatus.OK);
	}

	@Override
	public ArtificialPage<ProjectDTO> getUserProjectsWithPaginationAndStartsWithName(long userId, String filterName, int pageNumber) {
		return userService.getUserProjectsWithPagination(userId, filterName, pageNumber);
	}
}
