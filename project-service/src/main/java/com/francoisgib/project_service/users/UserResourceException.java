package com.francoisgib.project_service.users;

import com.francoisgib.project_service.exceptions.ResourceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserResourceException extends ResourceException {
	public UserResourceException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}
}
