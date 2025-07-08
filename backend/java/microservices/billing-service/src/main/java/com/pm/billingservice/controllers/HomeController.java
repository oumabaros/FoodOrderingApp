package com.pm.billingservice.controllers;

import com.pm.billingservice.config.BillingProperties;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    private final BillingProperties billingProperties;

    public HomeController(BillingProperties billingProperties) {
        this.billingProperties = billingProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return billingProperties.getGreeting();
    }
}
