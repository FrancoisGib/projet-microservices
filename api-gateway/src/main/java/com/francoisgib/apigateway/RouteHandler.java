package com.francoisgib.apigateway;

import com.francoisgib.apigateway.filters.RedirectToServiceFilter;
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

	@Value("${config.paths.kube-service}")
	private String kubeServicePath;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, RedirectToServiceFilter redirectToServiceFilter){
		RouteLocatorBuilder.Builder routeBuilder = builder.routes();
		projectServiceRouteLocator(routeBuilder);
		redirectToProjectServicesRouteLocator(routeBuilder, redirectToServiceFilter);
		return routeBuilder.build();
	}

	public void projectServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder
				.route("project-service", r -> r.path("/projects/**","/organizations/**", "/users/**", "/login")
						.filters(f -> f.dedupeResponseHeader("Access-Control-Allow-Origin", RETAIN_UNIQUE.name())
								.dedupeResponseHeader("Access-Control-Allow-Credentials", RETAIN_UNIQUE.name()))
						.uri(projectServicePath));
	}

	public void kubeServiceRouteLocator(RouteLocatorBuilder.Builder builder) {
		builder.route("kube-service", r -> r.path("/kube/**")
				.filters(f -> f.stripPrefix(1))
				.uri("http://localhost:8082"));
	}

	public void redirectToProjectServicesRouteLocator(RouteLocatorBuilder.Builder builder, RedirectToServiceFilter redirectToServiceFilter) {
		builder.route("service", r -> r.path("/{organization}/**")
				.filters(f -> f.filter(redirectToServiceFilter)).uri("no://op"));
	}
}
