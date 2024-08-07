package com.francoisgib.apigateway;

import com.francoisgib.apigateway.filters.AuthenticationFilter;
import com.francoisgib.apigateway.filters.LogFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RouteHandler {
	@Value("${config.paths.user-service}")
	private String userServicePath;

	@Value("${config.paths.project-service}")
	private String projectServicePath;

	@Value("${config.paths.docker-registry}")
	private String dockerRegistryPath;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authFilter, LogFilter logFilter) {
		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
		dockerRegistryRouteLocator(routeBuilder, authFilter);
		userServiceRouteLocator(routeBuilder);
		projectServiceRouteLocator(routeBuilder);
		organizationServiceRouteLocator(routeBuilder, logFilter);
		return routeBuilder.build();
	}

	public void dockerRegistryRouteLocator(RouteLocatorBuilder.Builder builder, AuthenticationFilter authFilter) {
		builder.route("docker-registry", r -> r.path("/v2/**")
				.filters(f -> f.filter(authFilter)).uri("http://" + dockerRegistryPath));
	}

	public void userServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
			.route("user-service", r -> r.path("/users/**")
					.uri(userServicePath));
	}

	public void projectServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
				.route("project-service", r -> r.path("/projects/**")
						.uri(projectServicePath));
	}

	public void organizationServiceRouteLocator(RouteLocatorBuilder.Builder builder, LogFilter logFilter) {
		builder.route("dynamic_route", r -> r
				.path("/{organization}/**")
						.filters(f -> f.filter(logFilter)).uri("no://op"))
				.build();
	}
}
