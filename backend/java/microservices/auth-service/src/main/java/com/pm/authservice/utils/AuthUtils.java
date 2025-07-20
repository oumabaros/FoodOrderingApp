package com.pm.authservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class AuthUtils {

    public static String getJwt(Authentication authentication){
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {

            return jwtAuthenticationToken.getToken().getTokenValue();
        }
        return null;
    }
    public static String getAuthId(Authentication authentication){
        String authId;
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            authId = jwt.getClaimAsString("sub");
            return authId;
        }
        return null;
    }
}
