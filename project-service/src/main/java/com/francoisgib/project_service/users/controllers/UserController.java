package com.francoisgib.project_service.users.controllers;

import com.francoisgib.project_service.ArtificialPage;
import com.francoisgib.project_service.ResponseObject;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.projects.user_project.UserProject;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserDTO;
import com.francoisgib.project_service.users.models.UserUpdateForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public interface UserController {
	@GetMapping
	ResponseEntity<List<UserDTO>> getAllUsers();

	@GetMapping("/email/{email}")
	ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws UserResourceException;

	@GetMapping("/username/{username}")
	ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws UserResourceException;

	@GetMapping("/{id}")
	ResponseEntity<UserDTO> getUserById(@PathVariable long id) throws UserResourceException;

	@PostMapping
	ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationForm userCreationForm) throws UserResourceException;

	@PutMapping("/{id}")
	ResponseEntity<UserDTO> updateUser(@Valid @PathVariable long id, @RequestBody UserUpdateForm userUpdateForm) throws UserResourceException;

	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) throws UserResourceException;
}
