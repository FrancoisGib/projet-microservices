package com.francoisgib.apigateway;

import com.francoisgib.apigateway.filters.AuthenticationFilter;
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
	public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authFilter) {
		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
		routeBuilder.route("docker-registry", r -> r.path("/v2/**").uri("http://" + dockerRegistryPath + "/v2/**"));
		userServiceRouteLocator(routeBuilder);
		projectServiceRouteLocator(routeBuilder, authFilter);
		return routeBuilder.build();
	}

	public void userServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
			.route("user-service", r -> r.path("/**")
				.and().uri("http://" + userServicePath + "/**"));
	}

	public void projectServiceRouteLocator(RouteLocatorBuilder.Builder builder, AuthenticationFilter authFilter) {
		builder
			.route("project-service", r -> r.path("/projects/**")
				.and().method("GET", "POST", "PUT").filters(f -> f.filter(authFilter)
				).uri("http://" + projectServicePath + "/projects/**"));
	}
}
