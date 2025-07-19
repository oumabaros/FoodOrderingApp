package com.pm.apigateway.controllers;

import com.pm.apigateway.ApiGatewayApplication;
import com.pm.apigateway.config.ApiGatewayProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

public class HomeController {
    private final ApiGatewayProperties apiGatewayProperties;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayApplication.class);
    public HomeController(ApiGatewayProperties apiGatewayProperties) {
        this.apiGatewayProperties= apiGatewayProperties;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return apiGatewayProperties.getGreeting();
    }
    @GetMapping(value = "/token")
    public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
    @GetMapping("/")
    public Mono<String> index(WebSession session) {
        return Mono.just(session.getId());
    }
}
