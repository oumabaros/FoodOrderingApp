package com.pm.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${auth0.front-end}")
    private String frontEnd;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        List<String> allowedUrls = new ArrayList<>();
        allowedUrls.add(frontEnd);
        allowedUrls.add("http://localhost:4004");
        registry.addMapping("/**") // Apply to all paths
                .allowedOrigins(frontEnd,"http://localhost:4004") // Specific origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allowed headers
                .allowCredentials(true) // Allow credentials (e.g., cookies, authorization headers)
                .maxAge(3600); // Max age for pre-flight requests (in seconds)
    }
}
