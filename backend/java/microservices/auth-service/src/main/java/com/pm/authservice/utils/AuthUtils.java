package com.pm.authservice.utils;

import com.pm.authservice.dto.CreateUserResponseDTO;
import com.pm.authservice.exception.UserNotFoundException;
import com.pm.authservice.mapper.UserMapper;
import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUtils {
    private static final Logger log = LoggerFactory.getLogger(
            AuthUtils.class);
    private static UserRepository userRepository;
    public AuthUtils(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public static String getUserId(String authId){
        User user = userRepository.findByAuth0Id(authId).orElseThrow(
                () -> new UserNotFoundException("User not found. "));

        return user.getId();
    }
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

    public static String getAuth0Id() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken token && token.getPrincipal() instanceof DefaultOidcUser user) {
            OidcIdToken idToken = user.getIdToken();
            log.info("Token raw value: {}", idToken.getTokenValue());
            log.info("Token claims map: {}", idToken.getClaims());
            log.info("AuthId: {}", idToken.getClaimAsString("sub"));
            return idToken.getClaimAsString("sub");
        }
        else if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            return jwt.getClaimAsString("sub");
        }
        else{
            throw new IllegalStateException("Oauth2 Security Context not found!");
        }
    }

    public static String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken token && token.getPrincipal() instanceof DefaultOidcUser user) {
            OidcIdToken idToken = user.getIdToken();
            log.info("Token raw value: {}", idToken.getTokenValue());
            log.info("Token claims map: {}", idToken.getClaims());
            log.info("AuthId: {}", idToken.getClaimAsString("sub"));
            return idToken.getClaimAsString("email");
        }
        else if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            return jwt.getClaimAsString("sub");
        }
        else{
            throw new IllegalStateException("Oauth2 Security Context not found!");
        }
    }
}
