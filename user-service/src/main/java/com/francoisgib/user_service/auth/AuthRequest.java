package com.francoisgib.user_service.auth;

public record AuthRequest(
	String username,
	String password) {
}