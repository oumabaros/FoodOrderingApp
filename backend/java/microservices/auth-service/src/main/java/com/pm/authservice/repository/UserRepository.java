package com.pm.authservice.repository;

import com.pm.authservice.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
  Optional<User> findByEmail(String email);
  Optional<User> findByAuth0Id(String auth0Id);
  boolean existsByAuth0Id(String auth0Id);
}