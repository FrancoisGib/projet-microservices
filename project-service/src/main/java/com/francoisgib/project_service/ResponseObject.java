package com.francoisgib.project_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ResponseObject(
	String message,
	int code) {

	public static ResponseEntity<ResponseObject> buildResponse(String message, HttpStatus status) {
		return new ResponseEntity<>(new ResponseObject(message, status.value()), status);
	}
}