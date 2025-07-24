package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.dto.validators.CreateRestaurantValidationGroup;
import com.pm.restaurantservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/my/restaurants")
@Tag(name = "Restaurant", description = "API for managing Restaurants")
public class RestaurantController {
  private final RestaurantService restaurantService;

  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @GetMapping("/authid")
  @Operation(summary = "Get Auth0Id")
  public ResponseEntity<String> getAuthId(Authentication authentication) {

   if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
      Jwt jwt = jwtAuthenticationToken.getToken();
      return ResponseEntity.ok().body(jwt.getClaimAsString("sub"));

    }
    else{
      throw new IllegalStateException("Oauth2 Security Context not found!");
    }

  }
  @GetMapping
  @Operation(summary = "Get Restaurants")
  public ResponseEntity<List<RestaurantResponseDTO>> getRestaurants() {
    List<RestaurantResponseDTO> restaurants = restaurantService.getRestaurants();
    return ResponseEntity.ok().body(restaurants);
  }

  @PostMapping
  @Operation(summary = "Create a new Restaurant")
  public ResponseEntity<RestaurantResponseDTO> createRestaurant(
      @Validated({Default.class, CreateRestaurantValidationGroup.class})
      @RequestBody RestaurantRequestDTO restaurantRequestDTO,
      @RequestParam("imageUrl") MultipartFile imageUrl) {

    RestaurantResponseDTO restaurantResponseDTO = restaurantService.createRestaurant(
        restaurantRequestDTO,imageUrl);

    return ResponseEntity.ok().body(restaurantResponseDTO);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a new Restaurant")
  public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable UUID id,
      @Validated({Default.class}) @RequestBody RestaurantRequestDTO restaurantRequestDTO) {

    RestaurantResponseDTO restaurantResponseDTO = restaurantService.updateRestaurant(id,
        restaurantRequestDTO);

    return ResponseEntity.ok().body(restaurantResponseDTO);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Restaurant")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity.noContent().build();
  }
}
