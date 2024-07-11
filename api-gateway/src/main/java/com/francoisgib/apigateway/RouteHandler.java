package com.francoisgib.apigateway;

import com.francoisgib.apigateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RouteHandler {
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authFilter) {
		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
		userServiceRouteLocator(routeBuilder);
		projectServiceRouteLocator(routeBuilder, authFilter);
		return routeBuilder.build();
	}

	public void userServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
			.route("user-service", r -> r.path("/login")
				.and().uri("http://localhost:8081/login"))
			.route("user-service", r -> r.path("/register")
				.and().uri("http://localhost:8081/register"));
	}

	public void projectServiceRouteLocator(RouteLocatorBuilder.Builder builder, AuthenticationFilter authFilter) {
		builder
			.route("project-service", r -> r.path("/projects/**")
				.and().method("GET", "POST", "PUT").filters(f -> f.filter(authFilter)
				).uri("http://localhost:8081/projects/**"));
	}
}
