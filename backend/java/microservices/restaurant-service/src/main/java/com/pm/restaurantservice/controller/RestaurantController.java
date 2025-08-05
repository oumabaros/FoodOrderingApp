package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@Tag(name = "MyRestaurant", description = "API for carrying out various restaurant tasks.")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @GetMapping("/search{/city}")
    @Operation(summary = "Search restaurants within a given city")
    public ResponseEntity<RestaurantResponseDTO> searchRestaurantInCity(@PathVariable String city) {

        RestaurantResponseDTO restaurantResponseDTO = restaurantService.searchRestaurantIncity(city);

        return ResponseEntity.ok().body(restaurantResponseDTO);
    }
}
