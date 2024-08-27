package com.francoisgib.project_service.users.services;

import com.francoisgib.project_service.ArtificialPage;
import com.francoisgib.project_service.projects.models.ProjectDTO;
import com.francoisgib.project_service.users.UserResourceException;
import com.francoisgib.project_service.users.models.User;
import com.francoisgib.project_service.users.models.UserCreationForm;
import com.francoisgib.project_service.users.models.UserUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
	List<User> getAllUsers();
	User findByEmail(String email) throws UserResourceException;
	User findByUsername(String username) throws UserResourceException;
	User getUser(long id) throws UserResourceException;
	User createUser(UserCreationForm userCreationForm) throws UserResourceException;
	User updateUser(Long id, UserUpdateForm userUpdateForm) throws UserResourceException;
	void deleteUser(long id) throws UserResourceException;
	ArtificialPage<ProjectDTO> getUserProjectsWithPagination(long userId, String filterName, int pageNumber);
}
