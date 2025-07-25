package com.pm.restaurantservice.mapper;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.model.MenuItem;
import com.pm.restaurantservice.model.Restaurant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RestaurantMapper {
  public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
    RestaurantResponseDTO restaurantDTO = new RestaurantResponseDTO();
    restaurantDTO.setId(restaurant.getId().toString());
    restaurantDTO.setRestaurantName(restaurant.getRestaurantName());
    restaurantDTO.setCity(restaurant.getCity());
    restaurantDTO.setCountry(restaurant.getCountry());
    restaurantDTO.setDeliveryPrice(restaurant.getDeliveryPrice());
    restaurantDTO.setEstimatedDeliveryTime(restaurant.getEstimatedDeliveryTime());
    restaurantDTO.setImageUrl(restaurant.getImageUrl());
    restaurantDTO.setLastUpdated(restaurant.getLastUpdated());
    restaurantDTO.setMenuItems((List<MenuItem>) restaurant.getMenuItems());
    restaurantDTO.setUserId(restaurant.getUserId());
    restaurantDTO.setCuisines(restaurant.getCuisines());

    return restaurantDTO;
  }

  public static Restaurant toModel(RestaurantRequestDTO restaurantRequestDTO) {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
    restaurant.setCity(restaurantRequestDTO.getCity());
    restaurant.setCountry(restaurantRequestDTO.getCountry());
    restaurant.setDeliveryPrice(restaurantRequestDTO.getDeliveryPrice());
    restaurant.setEstimatedDeliveryTime(restaurantRequestDTO.getEstimatedDeliveryTime());
    //restaurant.setImageUrl(restaurantRequestDTO.getImageUrl());
    restaurant.setLastUpdated(restaurantRequestDTO.getLastUpdated());
    restaurant.setMenuItems((Set<MenuItem>) restaurantRequestDTO.getMenuItems());
    restaurant.setUserId(restaurantRequestDTO.getUserId());
    restaurant.setCuisines(restaurantRequestDTO.getCuisines());
    return restaurant;
  }
}
