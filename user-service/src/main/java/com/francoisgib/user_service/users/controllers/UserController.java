package com.francoisgib.user_service.users.controllers;

import com.francoisgib.user_service.ResponseObject;
import com.francoisgib.user_service.users.UserResourceException;
import com.francoisgib.user_service.users.models.UserCreationForm;
import com.francoisgib.user_service.users.models.UserDTO;
import com.francoisgib.user_service.users.models.UserUpdateForm;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping
	ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationForm userCreationForm);

	@PostMapping("/{id}")
	ResponseEntity<UserDTO> updateUser(@Valid @PathVariable long id, @RequestBody UserUpdateForm userUpdateForm) throws UserResourceException;

	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) throws UserResourceException;
}
