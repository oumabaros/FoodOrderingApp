package com.pm.restaurantservice.repository;

import com.pm.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    boolean existsByRestaurantName(String restaurantName);
    boolean existsByUserId(String userId);
    Optional<Restaurant> findById(String _id);

}
