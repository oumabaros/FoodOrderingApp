package com.pm.restaurantservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class Utilities {
    @Autowired
    private static RestTemplate restTemplate;

    public static String getUserId(String authId) {
        String url = "http://localhost:4004/api/my/user/" + authId; // Replace with actual user service URL
        return restTemplate.getForObject(url, String.class);
    }
}
