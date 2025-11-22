package com.cognizant.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
 
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
 
    @Autowired
    private RouteValidator routeValidator;
 
    public AuthenticationFilter() {
        super(Config.class);
    }
 
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
 
            System.out.println("Incoming request path: " + path);
 
            if (routeValidator.isSecured.test(request)) {
                System.out.println("Secured route detected");
 
                
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    System.out.println("Missing Authorization header");
                    exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
 
             
                String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                System.out.println("Token received: " + token);
 
                
            } else {
                System.out.println("Public route — no auth required");
            }
 
            return chain.filter(exchange);
        };
    }
 
    public static class Config {
        
    }
}
 