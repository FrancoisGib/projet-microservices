package com.francoisgib.project_service.auth;

public record AuthRequest(
	String username,
	String password) {
}