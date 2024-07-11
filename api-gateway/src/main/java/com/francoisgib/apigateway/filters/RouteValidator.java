package com.francoisgib.apigateway.filters;

import java.util.List;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	public static final List<String> unprotectedURLs = List.of("/login", "/register");

	public boolean isProtectedRoute(ServerHttpRequest request) {
		return unprotectedURLs.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
	}
}