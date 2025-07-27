package com.pm.restaurantservice.mapper;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.model.Restaurant;

import java.util.Optional;


public class RestaurantMapper {
    public static Optional<RestaurantResponseDTO> toOptionalDTO(Optional<Restaurant> restaurant) {
        return restaurant.map(res->{
            RestaurantResponseDTO restaurantDTO = new RestaurantResponseDTO();
            restaurantDTO.setRestaurantName(res.getRestaurantName());
            restaurantDTO.setCity(res.getCity());
            restaurantDTO.setCountry(res.getCountry());
            restaurantDTO.setDeliveryPrice(res.getDeliveryPrice());
            restaurantDTO.setEstimatedDeliveryTime(res.getEstimatedDeliveryTime());
            restaurantDTO.setImageUrl(res.getImageUrl());
            restaurantDTO.setLastUpdated(res.getLastUpdated());
            restaurantDTO.setCuisines(res.getCuisines());
            restaurantDTO.setMenuItems(res.getMenuItems());
            return restaurantDTO;
        });

    }

    public static Optional<Restaurant> toOptionalModel(Optional<RestaurantRequestDTO> restaurantRequestDTO) {
        return restaurantRequestDTO.map(res->{
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(res.getRestaurantName());
            restaurant.setCity(res.getCity());
            restaurant.setCountry(res.getCountry());
            restaurant.setDeliveryPrice(res.getDeliveryPrice());
            restaurant.setEstimatedDeliveryTime(res.getEstimatedDeliveryTime());
            restaurant.setImageUrl(res.getImageUrl());
            restaurant.setLastUpdated(res.getLastUpdated());
            restaurant.setCuisines(res.getCuisines());
            restaurant.setMenuItems(res.getMenuItems());
            return restaurant;
        });

    }

    public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
        RestaurantResponseDTO restaurantDTO = new RestaurantResponseDTO();
        restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setCountry(restaurant.getCountry());
        restaurantDTO.setDeliveryPrice(restaurant.getDeliveryPrice());
        restaurantDTO.setEstimatedDeliveryTime(restaurant.getEstimatedDeliveryTime());
        restaurantDTO.setImageUrl(restaurant.getImageUrl());
        restaurantDTO.setLastUpdated(restaurant.getLastUpdated());
        restaurantDTO.setCuisines(restaurant.getCuisines());
        restaurantDTO.setMenuItems(restaurant.getMenuItems());
        return restaurantDTO;
    }

    public static Restaurant toModel(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
        restaurant.setCity(restaurantRequestDTO.getCity());
        restaurant.setCountry(restaurantRequestDTO.getCountry());
        restaurant.setDeliveryPrice(restaurantRequestDTO.getDeliveryPrice());
        restaurant.setEstimatedDeliveryTime(restaurantRequestDTO.getEstimatedDeliveryTime());
        restaurant.setImageUrl(restaurantRequestDTO.getImageUrl());
        restaurant.setLastUpdated(restaurantRequestDTO.getLastUpdated());
        restaurant.setCuisines(restaurantRequestDTO.getCuisines());
        restaurant.setMenuItems(restaurantRequestDTO.getMenuItems());
        return restaurant;
    }
}
