package com.pm.authservice.controller;

import com.pm.authservice.config.AuthProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final AuthProperties authProperties;

    public HomeController(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return authProperties.getGreeting();
    }
}
