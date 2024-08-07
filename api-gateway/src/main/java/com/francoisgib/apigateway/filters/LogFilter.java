package com.francoisgib.apigateway.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogFilter implements GatewayFilter, Ordered {
    // Netty filter order
    public static final int ORDER = 10001;

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest originalRequest = exchange.getRequest();
        String path = originalRequest.getPath().value();
        String[] pathParts = path.split("/", 3);

        if (pathParts.length < 2) {
            log.warn("Path does not contain enough parts: {}", path);
            return chain.filter(exchange);
        }

        String organization = pathParts[1];
        String remainingPath = pathParts.length > 2 ? "/" + pathParts[2] : "";

        URI uri = URI.create("http://" + organization + remainingPath);
        ServerHttpRequest mutatedRequest = originalRequest.mutate()
                .uri(uri)
                .path(remainingPath)
                .build();

        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, uri);
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        log.info("Redirect URI: {}", mutatedExchange.getRequest().getURI());

        return chain.filter(mutatedExchange);
    }
}