package com.pm.apigateway.config;

import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CorsWebFilter corsWebFilter(GlobalCorsProperties properties) {
        CorsConfiguration corsConfig = properties.getCorsConfigurations().get("/**");
        corsConfig.setAllowedOriginPatterns(List.of("http://localhost:5173"));
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}