package com.francoisgib.project_service.users;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserResourceException extends Exception {
	private final int statusCode;

	public UserResourceException(HttpStatus status, String message) {
		super(message);
		this.statusCode = status.value();
	}
}
