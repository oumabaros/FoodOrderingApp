package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.service.RestaurantService;
import com.pm.restaurantservice.utils.RestaurantRequestParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;




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
        Optional<RestaurantResponseDTO> restaurantResponseDTO = restaurantService.getRestaurantByUser(authentication);
        if (restaurantResponseDTO.isPresent()) {
            RestaurantResponseDTO rest = restaurantResponseDTO.get();
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/authid")
    @Operation(summary = "Get Auth0Id")
    public ResponseEntity<String> getAuthId(Authentication authentication) {

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            return ResponseEntity.ok().body(jwt.getClaimAsString("sub"));
        } else {
            throw new IllegalStateException("Oauth2 Security Context not found!");
        }

    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Create a new Restaurant")
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(
            HttpServletRequest request,
            Authentication authentication) {
        try {
            RestaurantRequestDTO reqDTO = RestaurantRequestParser.parseRestaurantRequest(request);
            Optional<RestaurantResponseDTO> restaurantResponseDTO = restaurantService.createRestaurant(
                    reqDTO, authentication);
            if (restaurantResponseDTO.isPresent()) {
                RestaurantResponseDTO rest = restaurantResponseDTO.get();
                return new ResponseEntity<>(rest, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = "multipart/form-data")
    @Operation(summary = "Update a new Restaurant")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            HttpServletRequest request,
            Authentication authentication) {

        try {
            RestaurantRequestDTO reqDTO = RestaurantRequestParser.parseRestaurantRequest(request);
            System.out.println("DTO UPDATE: "+reqDTO);
            Optional<RestaurantResponseDTO> restaurantResponseDTO = restaurantService.createRestaurant(
                    reqDTO, authentication);
            if (restaurantResponseDTO.isPresent()) {
                RestaurantResponseDTO rest = restaurantResponseDTO.get();
                return new ResponseEntity<>(rest, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}