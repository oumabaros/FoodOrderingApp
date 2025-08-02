package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.exception.RestaurantNotFoundException;
import com.pm.restaurantservice.service.RestaurantService;
import com.pm.restaurantservice.utils.RestaurantRequestParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/my/restaurant")
@Tag(name = "Restaurant", description = "API for managing Restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @GetMapping
    @Operation(summary = "Get Restaurant")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(Authentication authentication) {
        RestaurantResponseDTO restaurantResponseDTO = restaurantService.getRestaurantByUser(authentication);
        if(restaurantResponseDTO!=null){
            return new ResponseEntity<>(restaurantResponseDTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new RestaurantResponseDTO(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/authid")
    @Operation(summary = "Get Auth0Id")
    public ResponseEntity<String> getAuthId(Authentication authentication) {

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            return ResponseEntity.ok().body(jwt.getClaimAsString("sub"));
        } else {
            throw new SecurityException("Oauth2 Security Context not found!");
        }

    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Create a new Restaurant")
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(
            HttpServletRequest request,
            Authentication authentication) {
        try {
            RestaurantRequestDTO reqDTO = RestaurantRequestParser.parseRestaurantRequest(request);
            RestaurantResponseDTO restaurantResponseDTO = restaurantService.createRestaurant(
                    reqDTO, authentication);
            return ResponseEntity.ok().body(restaurantResponseDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "Update a new Restaurant")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            HttpServletRequest request,
            Authentication authentication) {
        try {
            RestaurantRequestDTO reqDTO = RestaurantRequestParser.parseRestaurantRequestUpdate(request);
            RestaurantResponseDTO restaurantResponseDTO = restaurantService.updateRestaurant(
                    reqDTO, authentication);
            return ResponseEntity.ok().body(restaurantResponseDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }
}