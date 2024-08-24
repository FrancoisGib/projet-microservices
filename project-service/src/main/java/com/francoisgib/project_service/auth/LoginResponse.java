package com.francoisgib.project_service.auth;

import org.springframework.http.ResponseCookie;

public record LoginResponse(ResponseCookie cookie, AuthResponse authResponse) {
}