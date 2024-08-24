package com.francoisgib.project_service.auth;

public record AuthResponse(Long id, String username, int organizationId, String organizationName) {
}
