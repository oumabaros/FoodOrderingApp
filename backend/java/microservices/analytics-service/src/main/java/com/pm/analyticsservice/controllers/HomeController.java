package com.pm.analyticsservice.controllers;

import com.pm.analyticsservice.config.AnalyticsProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final AnalyticsProperties analyticsProperties;

    public HomeController(AnalyticsProperties analyticsProperties) {
        this.analyticsProperties = analyticsProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return analyticsProperties.getGreeting();
    }
}
