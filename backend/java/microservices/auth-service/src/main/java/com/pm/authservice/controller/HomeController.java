package com.pm.authservice.controller;

import com.pm.authservice.config.AuthProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


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

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities();
    }
}
