package com.francoisgib.project_service.users.services;

import com.francoisgib.project_service.MessageService;
import com.francoisgib.project_service.organizations.OrganizationService;
import com.francoisgib.project_service.organizations.models.Organization;
import com.francoisgib.project_service.users.UserRepository;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserUpdateForm;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	private final OrganizationService organizationService;

	private final PasswordEncoder passwordEncoder;

	private final MessageService messageService;

	@Override
	public List<User> getAllUsers() {
		messageService.sendLogMessage("Retrieving all users");
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) throws UserResourceException {
		messageService.sendLogMessage("Retrieving user with email: " + email);
		return userRepository.findByEmail(email).orElseThrow(() -> new UserResourceException(HttpStatus.NOT_FOUND, "User with email " + email + " not found"));
	}

	@Override
	public User getUser(long id) throws UserResourceException {
		messageService.sendLogMessage("Retrieving user with id: " + id);
		return userRepository.findById(id).orElseThrow(() -> new UserResourceException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
	}

	@Override
	public User findByUsername(String username) throws UserResourceException {
		messageService.sendLogMessage("Retrieving user with username: " + username);
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UserResourceException(HttpStatus.NOT_FOUND, "User with username " + username + " not found"));
	}

	@Override
	public User createUser(UserCreationForm userCreationForm) {
		messageService.sendLogMessage("Creating user : " + userCreationForm.getUsername());
		Organization organization = organizationService.getOrganizationById(userCreationForm.getOrganizationId());

		return userRepository.save(User.builder()
			.email(userCreationForm.getEmail())
			.username(userCreationForm.getUsername())
			.organization(organization)
			.password(passwordEncoder.encode(userCreationForm.getPassword()))
			.build());
	}

	@Override
	public User updateUser(Long id, UserUpdateForm userUpdateForm) throws UserResourceException {
		User user = userRepository.findById(id).orElseThrow(() -> new UserResourceException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
		boolean update = false;
		if (userUpdateForm.getEmail() != null && !user.getEmail().equals(userUpdateForm.getEmail())) {
			user.setEmail(userUpdateForm.getEmail());
			update = true;
		}
		if (userUpdateForm.getUsername() != null && !user.getUsername().equals(userUpdateForm.getUsername())) {
			user.setUsername(userUpdateForm.getUsername());
			update = true;
		}
		if (userUpdateForm.getPassword() != null && !user.getPassword().equals(userUpdateForm.getPassword())) {
			user.setPassword(passwordEncoder.encode(userUpdateForm.getPassword()));
			update = true;
		}
		if (!(user.getOrganization().getId() == userUpdateForm.getOrganizationId())) {
			Organization organization = organizationService.getOrganizationById(userUpdateForm.getOrganizationId());
			user.setOrganization(organization);
			update = true;
		}
		if (update) {
			messageService.sendLogMessage("Updating user : " + userUpdateForm.getUsername());
			return userRepository.save(user);
		} else {
			return user;
		}
	}

	@Override
	public void deleteUser(long id) {
		messageService.sendLogMessage("Deleting user with id : " + id);
		userRepository.deleteById(id);
	}
}
