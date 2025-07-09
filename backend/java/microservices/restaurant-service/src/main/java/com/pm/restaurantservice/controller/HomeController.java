package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.config.RestaurantProperties;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    private final RestaurantProperties restaurantProperties;

    public HomeController(RestaurantProperties restaurantProperties) {
        this.restaurantProperties = restaurantProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return restaurantProperties.getGreeting();
    }
}
