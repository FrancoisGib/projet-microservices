package com.francoisgib.user_service.users.services;

import com.francoisgib.user_service.users.UserResourceException;
import com.francoisgib.user_service.users.models.User;
import com.francoisgib.user_service.users.models.UserCreationForm;
import com.francoisgib.user_service.users.models.UserUpdateForm;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	List<User> getAllUsers();
	User getByEmail(String email) throws UserResourceException;
	User getByUsername(String username) throws UserResourceException;
	User getUser(long id) throws UserResourceException;
	User createUser(UserCreationForm userCreationForm);
	User updateUser(Long id, UserUpdateForm userUpdateForm) throws UserResourceException;
	void deleteUser(long id) throws UserResourceException;
}
