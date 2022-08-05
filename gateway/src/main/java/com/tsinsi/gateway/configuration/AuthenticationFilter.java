package com.tsinsi.gateway.configuration;

import com.tsinsi.gateway.configuration.util.MapClaims;
import org.apache.logging.log4j.util.Strings;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter {

    @Autowired
    private Whitelist whitelist;

    @Autowired
    private Hashids hashids;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (whitelist.getPaths().stream().noneMatch(uri -> request.getURI().getPath().contains(uri))) {
            String bearerToken = getToken(request);
            if (bearerToken == null || !bearerToken.startsWith("Bearer")) {
                return onError(exchange, HttpStatus.FORBIDDEN);
            }

            String token = bearerToken.substring(7);
            if (Strings.isEmpty(token)) {
                return onError(exchange, HttpStatus.FORBIDDEN);
            }
            MapClaims mapClaims = jwtHelper.parse(token);
            if (!mapClaims.valid()) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            String subject = mapClaims.subject();
            long[] id = hashids.decode(subject);
            exchange.getRequest().mutate().header("id", String.valueOf(id[0])).build();
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getToken(ServerHttpRequest request) {
        List<String> list = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
