package com.pm.restaurantservice.repository;

import com.pm.restaurantservice.model.Restaurant;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, UUID> {
    boolean existsByRestaurantName(String restaurantName);
}
