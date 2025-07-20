package com.pm.authservice.controller;

import com.pm.authservice.config.AuthProperties;
import com.pm.authservice.utils.AuthUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Map;



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

    @RequestMapping(value = "/my/user/ping", method = RequestMethod.GET)
    public String ping() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return "Scopes: " + authentication.getAuthorities() +
                "\n\n Details: " + authentication.getDetails()+
                "\n\n Principal: " + authentication.getPrincipal().toString();
    }

    @RequestMapping(value="/my/user/user",method = RequestMethod.GET)
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @RequestMapping(value="/my/user/info",method = RequestMethod.GET)
    public Object getCurrentUser() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication.getCredentials();
    }

    @RequestMapping(value="/my/user/auth-id",method = RequestMethod.GET)
    public String getAuthId(Authentication authentication) {
        String authId;
        if(AuthUtils.getAuthId(authentication)!=null){
            authId = AuthUtils.getAuthId(authentication);

        }
        else{
            return null;
        }

        return authId;
    }

    @RequestMapping(value="/my/user/auth-token",method = RequestMethod.GET)
    public String getAuthToken(Authentication authentication) {
        String authToken;
        if(AuthUtils.getJwt(authentication)!=null){
            authToken = AuthUtils.getJwt(authentication);

        }
        else{
            return null;
        }

        return authToken;
    }
}
