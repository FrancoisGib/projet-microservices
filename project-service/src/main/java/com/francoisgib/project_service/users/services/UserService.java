package com.francoisgib.project_service.users.services;

import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserUpdateForm;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	List<User> getAllUsers();
	User findByEmail(String email) throws UserResourceException;
	User findByUsername(String username) throws UserResourceException;
	User getUser(long id) throws UserResourceException;
	User createUser(UserCreationForm userCreationForm);
	User updateUser(Long id, UserUpdateForm userUpdateForm) throws UserResourceException;
	void deleteUser(long id) throws UserResourceException;
}
