package com.pm.restaurantservice.mapper;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.model.Restaurant;


public class RestaurantMapper {
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
