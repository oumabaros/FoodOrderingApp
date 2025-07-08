package com.pm.apigateway.controllers;

import com.pm.apigateway.config.ApiGatewayProperties;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    private final ApiGatewayProperties apiGatewayProperties;

    public HomeController(ApiGatewayProperties apiGatewayProperties) {
        this.apiGatewayProperties= apiGatewayProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return apiGatewayProperties.getGreeting();
    }
}
