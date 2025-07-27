package com.pm.restaurantservice.repository;

import com.pm.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    boolean existsByAuth0Id(String auth0Id);
    Optional<Restaurant> findByUser(String user);
    Optional<Restaurant> findByAuth0Id(String auth0Id);


}
