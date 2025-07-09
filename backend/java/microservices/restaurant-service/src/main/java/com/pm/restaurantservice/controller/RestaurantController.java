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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurant", description = "API for managing Restaurants")
public class RestaurantController {

  private final RestaurantService restaurant;

  public RestaurantController(RestaurantService restaurant) {
    this.restaurant = restaurant;
  }

  @GetMapping
  @Operation(summary = "Get Restaurants")
  public ResponseEntity<List<RestaurantResponseDTO>> getRestaurants() {
    List<RestaurantResponseDTO> restaurants = restaurant.getRestaurants();
    return ResponseEntity.ok().body(restaurants);
  }

  @PostMapping
  @Operation(summary = "Create a new Restaurant")
  public ResponseEntity<RestaurantResponseDTO> createRestaurant(
      @Validated({Default.class, CreateRestaurantValidationGroup.class})
      @RequestBody RestaurantRequestDTO restaurantRequestDTO) {

    RestaurantResponseDTO restaurantResponseDTO = restaurant.createRestaurant(
        restaurantRequestDTO);

    return ResponseEntity.ok().body(restaurantResponseDTO);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a new Restaurant")
  public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable UUID id,
      @Validated({Default.class}) @RequestBody RestaurantRequestDTO restaurantRequestDTO) {

    RestaurantResponseDTO restaurantResponseDTO = restaurant.updateRestaurant(id,
        restaurantRequestDTO);

    return ResponseEntity.ok().body(restaurantResponseDTO);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Restaurant")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
    restaurant.deleteRestaurant(id);
    return ResponseEntity.noContent().build();
  }
}
