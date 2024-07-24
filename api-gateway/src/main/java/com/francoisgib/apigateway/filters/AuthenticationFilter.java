package com.francoisgib.apigateway.filters;

import com.francoisgib.apigateway.jwt.JwtService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {
	private final RouteValidator routeValidator = new RouteValidator();

	private final JwtService jwtService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		log.info(request.getURI().getPath());
		return chain.filter(exchange);
	}
	/*
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		if (routeValidator.isProtectedRoute(request)) {
			log.info("Checking token existence");
			final HttpCookie cookie = request.getCookies().toSingleValueMap().get("accessToken");
			if (cookie == null) {
				return this.onError(exchange,"No token");
			}

			final String token = cookie.getValue();
			log.info("Token : {}", token);
			String username = jwtService.extractUsername(token);
			log.info("Username {}", username);
			if (username == null || !jwtService.validateToken(token)) {
				return this.onError(exchange,"Invalid token");
			}
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username, null, null);
			return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
		}
		return this.onError(exchange,"Credentials missing");
	}*/

	private Mono<Void> onError(ServerWebExchange exchange, String err) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		log.info("Error : {}", err);
		return response.setComplete();
	}
}