//package com.pm.authservice.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//public class SecurityConfig {
//    @Value("${auth0.audience}")
//    private String audience;
//
//    @Value("${auth0.domain}")
//    private String domain;
//    @Value("${auth0.client-id}")
//    private String clientId;
//
//    public SecurityConfig() {
//
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/public/**").permitAll() // Example public endpoints
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
//                        .jwt(jwt -> jwt
//                                .decoder(jwtDecoder())
//                        )
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(domain);
//
//        jwtDecoder.setJwtValidator(JwtValidators.createAtJwtValidator()
//                .audience(audience)
//                .clientId(clientId)
//                .issuer("https://" + domain + "/").build());
//        return jwtDecoder;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
