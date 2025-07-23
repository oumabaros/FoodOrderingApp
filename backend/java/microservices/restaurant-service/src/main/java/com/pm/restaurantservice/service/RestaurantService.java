package com.pm.restaurantservice.service;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.exception.EmailAlreadyExistsException;
import com.pm.restaurantservice.exception.RestaurantNotFoundException;
import com.pm.restaurantservice.grpc.BillingServiceGrpcClient;
import com.pm.restaurantservice.kafka.KafkaProducer;
import com.pm.restaurantservice.mapper.RestaurantMapper;
import com.pm.restaurantservice.model.Restaurant;
import com.pm.restaurantservice.repository.RestaurantRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final BillingServiceGrpcClient billingServiceGrpcClient;
  private final KafkaProducer kafkaProducer;

  public RestaurantService(RestaurantRepository restaurantRepository,
      BillingServiceGrpcClient billingServiceGrpcClient,
      KafkaProducer kafkaProducer) {
    this.restaurantRepository = restaurantRepository;
    this.billingServiceGrpcClient = billingServiceGrpcClient;
    this.kafkaProducer = kafkaProducer;
  }

  public List<RestaurantResponseDTO> getRestaurants() {
    List<Restaurant> restaurants = restaurantRepository.findAll();

    return restaurants.stream().map(RestaurantMapper::toDTO).toList();
  }

  public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
    Restaurant newRestaurant = restaurantRepository.save(
        RestaurantMapper.toModel(restaurantRequestDTO));

    billingServiceGrpcClient.createBillingAccount(newRestaurant.getId().toString(),
        newRestaurant.getRestaurantName(), newRestaurant.getRestaurantName());

    kafkaProducer.sendEvent(newRestaurant);

    return RestaurantMapper.toDTO(newRestaurant);
  }

  public RestaurantResponseDTO updateRestaurant(UUID id,
      RestaurantRequestDTO restaurantRequestDTO) {

    Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
        () -> new RestaurantNotFoundException("Restaurant not found with ID: " + id));



    restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
    restaurant.setCity(restaurantRequestDTO.getCity());
    restaurant.setCountry(restaurantRequestDTO.getCountry());
    restaurant.setDeliveryPrice(restaurantRequestDTO.getDeliveryPrice());
    restaurant.setEstimatedDeliveryTime(restaurantRequestDTO.getEstimatedDeliveryTime());
    restaurant.setImageUrl(restaurantRequestDTO.getImageUrl());
    restaurant.setLastUpdated(restaurantRequestDTO.getLastUpdated());
    restaurant.setMenuItems(restaurantRequestDTO.getMenuItems());
    restaurant.setUserId(restaurantRequestDTO.getUserId());
    restaurant.setCuisines(restaurantRequestDTO.getCuisines());

    Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
    return RestaurantMapper.toDTO(updatedRestaurant);
  }

  public void deleteRestaurant(UUID id) {
    restaurantRepository.deleteById(id);
  }
}
