package com.pm.authservice.repository;

import com.pm.authservice.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
  Optional<User> findByAuth0Id(String auth0Id);
  boolean existsByAuth0Id(String auth0Id);
}
