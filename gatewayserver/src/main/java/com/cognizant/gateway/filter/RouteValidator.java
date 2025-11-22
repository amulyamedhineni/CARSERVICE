package com.cognizant.gateway.filter;

import java.util.List;

import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
 
@Component
public class RouteValidator {
 
    public static final List<String> openApiEndpoints = List.of(
    		"/AUTHSERVICE/api/register",
    	    "/AUTHSERVICE/api/token",
    	    "/AUTHSERVICE/api/validate",
    	    "/eureka"
    );
 
    
    public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getURI().getPath();
        System.out.println("Checking secured path: " + path);
        boolean secured = openApiEndpoints.stream().noneMatch(path::startsWith);
        System.out.println("Is secured? " + secured);
        return secured;
    };
 
}