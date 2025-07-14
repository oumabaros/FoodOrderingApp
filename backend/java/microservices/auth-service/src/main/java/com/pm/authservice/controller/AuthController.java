package com.pm.authservice.controller;

import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/profile")
    public Map<String, Object> getProfile(@AuthenticationPrincipal OAuth2User principal) {
        //return Collections.singletonMap("name", principal.getAttribute("name"));
        return principal.getAttributes();
    }
}
