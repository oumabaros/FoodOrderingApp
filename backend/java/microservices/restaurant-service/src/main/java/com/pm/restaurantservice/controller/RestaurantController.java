package com.pm.restaurantservice.controller;

import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.dto.SearchRestaurantResponseDTO;
import com.pm.restaurantservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurant")
@Tag(name = "MyRestaurant", description = "API for carrying out various restaurant tasks.")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @GetMapping("/search/{city}")
    @Operation(summary = "Search restaurants within a given city")
    public ResponseEntity<SearchRestaurantResponseDTO> searchRestaurant(
            @PathVariable String city,
            @RequestParam(name = "searchQuery", required = false,defaultValue = "") String searchQuery,
            @RequestParam(name = "selectedCuisines", required = false,defaultValue = "") String selectedCuisines,
            @RequestParam(name = "sortOption", required = false,defaultValue = "lastUpdated") String sortOption,
            @RequestParam(name = "page", required = false,defaultValue = "1") String page) {

        SearchRestaurantResponseDTO restaurantResponseDTO = restaurantService.searchRestaurant(
                city,
                searchQuery,
                selectedCuisines,
                sortOption,
                page
        );
        return ResponseEntity.ok().body(restaurantResponseDTO);
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Search restaurants within a given city")
    public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable String restaurantId) {

        RestaurantResponseDTO restaurantResponseDTO = restaurantService.getRestaurant(restaurantId);
        return ResponseEntity.ok().body(restaurantResponseDTO);
    }
}
