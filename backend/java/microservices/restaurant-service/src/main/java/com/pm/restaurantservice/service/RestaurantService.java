package com.pm.restaurantservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.exception.RestaurantNotFoundException;
import com.pm.restaurantservice.grpc.AuthServiceGrpcClient;
import com.pm.restaurantservice.grpc.BillingServiceGrpcClient;
import com.pm.restaurantservice.kafka.KafkaProducer;
import com.pm.restaurantservice.mapper.RestaurantMapper;
import com.pm.restaurantservice.model.MenuItem;
import com.pm.restaurantservice.model.Restaurant;
import com.pm.restaurantservice.repository.RestaurantRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final BillingServiceGrpcClient billingServiceGrpcClient;
  private final KafkaProducer kafkaProducer;
  private final AuthServiceGrpcClient authServiceGrpcClient;
  private final Cloudinary cloudinary;
  public RestaurantService(RestaurantRepository restaurantRepository,
      BillingServiceGrpcClient billingServiceGrpcClient,
                           AuthServiceGrpcClient authServiceGrpcClient,
      KafkaProducer kafkaProducer,Cloudinary cloudinary) {
    this.restaurantRepository = restaurantRepository;
    this.billingServiceGrpcClient = billingServiceGrpcClient;
    this.kafkaProducer = kafkaProducer;
    this.authServiceGrpcClient=authServiceGrpcClient;
    this.cloudinary=cloudinary;
  }

  public List<RestaurantResponseDTO> getRestaurants() {
    List<Restaurant> restaurants = restaurantRepository.findAll();

    return restaurants.stream().map(RestaurantMapper::toDTO).toList();
  }

  public String getAuth0Id(){
    return authServiceGrpcClient.getAuth0Id();
  }
  public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO,
                                                @RequestParam("imageUrl") MultipartFile imageUrl) {
    try {
      File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + imageUrl.getOriginalFilename());
      FileOutputStream fos = new FileOutputStream(convFile);
      fos.write(imageUrl.getBytes());
      fos.close();

      var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/bookCovers/"));

      Restaurant newRestaurant = new Restaurant();
      newRestaurant.setImageUrl(pic.get("url").toString());
              restaurantRepository.save(
              RestaurantMapper.toModel(restaurantRequestDTO));

      billingServiceGrpcClient.createBillingAccount(newRestaurant.getId().toString(),
              newRestaurant.getRestaurantName(), newRestaurant.getRestaurantName());

      kafkaProducer.sendEvent(newRestaurant);

      return RestaurantMapper.toDTO(newRestaurant);

    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the file.");
    }

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
    //restaurant.setImageUrl(restaurantRequestDTO.getImageUrl());
    restaurant.setLastUpdated(restaurantRequestDTO.getLastUpdated());
    restaurant.setMenuItems((Set<MenuItem>) restaurantRequestDTO.getMenuItems());
    restaurant.setUserId(restaurantRequestDTO.getUserId());
    restaurant.setCuisines(restaurantRequestDTO.getCuisines());

    Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
    return RestaurantMapper.toDTO(updatedRestaurant);
  }

  public void deleteRestaurant(UUID id) {
    restaurantRepository.deleteById(id);
  }
}
