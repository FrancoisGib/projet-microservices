package com.francoisgib.apigateway;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.gateway.filter.factory.DedupeResponseHeaderGatewayFilterFactory.Strategy.RETAIN_UNIQUE;

@RequiredArgsConstructor
@Component
public class RouteHandler {

	@Value("${config.paths.project-service}")
	private String projectServicePath;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
		projectServiceRouteLocator(routeBuilder);
		return routeBuilder.build();
	}

	public void projectServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
				.route("project-service", r -> r.path("/projects/**","/organizations/**", "/users/**", "/login")
						.filters(f -> f.dedupeResponseHeader("Access-Control-Allow-Origin", RETAIN_UNIQUE.name())
								.dedupeResponseHeader("Access-Control-Allow-Credentials", RETAIN_UNIQUE.name()))
						.uri(projectServicePath));
	}
}
