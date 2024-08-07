package com.francoisgib.apigateway.filters;

import com.francoisgib.apigateway.UserPrincipal;
import com.francoisgib.apigateway.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements GatewayFilter {
    private final RouteValidator routeValidator = new RouteValidator();

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (routeValidator.isProtectedRoute(request)) {
            final HttpCookie cookie = request.getCookies().toSingleValueMap().get("accessToken");
            if (cookie == null) {
                return this.onError(exchange, "No token");
            }

            final String token = cookie.getValue();
            String username = jwtService.extractUsername(token);
            String organization = jwtService.extractOrganization(token);
            if (username == null || organization == null || !jwtService.validateToken(token)) {
                return this.onError(exchange, "Invalid token");
            }

            UserPrincipal principal = new UserPrincipal(username, organization);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    principal, null, null);
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
        }
        return this.onError(exchange, "Credentials missing");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        log.info("Error : {}", err);
        return response.setComplete();
    }
}