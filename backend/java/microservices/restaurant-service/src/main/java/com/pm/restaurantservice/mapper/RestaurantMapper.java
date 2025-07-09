package com.pm.restaurantservice.mapper;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.model.Restaurant;
import java.time.LocalDate;

public class RestaurantMapper {
  public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
    RestaurantResponseDTO restaurantDTO = new RestaurantResponseDTO();
    restaurantDTO.setId(restaurant.getId().toString());
    restaurantDTO.setName(restaurant.getName());
    restaurantDTO.setAddress(restaurant.getAddress());
    restaurantDTO.setEmail(restaurant.getEmail());
    restaurantDTO.setDateOfBirth(restaurant.getDateOfBirth().toString());

    return restaurantDTO;
  }

  public static Restaurant toModel(RestaurantRequestDTO restaurantRequestDTO) {
    Restaurant restaurant = new Restaurant();
    restaurant.setName(restaurantRequestDTO.getName());
    restaurant.setAddress(restaurantRequestDTO.getAddress());
    restaurant.setEmail(restaurantRequestDTO.getEmail());
    restaurant.setDateOfBirth(LocalDate.parse(restaurantRequestDTO.getDateOfBirth()));
    restaurant.setRegisteredDate(LocalDate.parse(restaurantRequestDTO.getRegisteredDate()));
    return restaurant;
  }
}
