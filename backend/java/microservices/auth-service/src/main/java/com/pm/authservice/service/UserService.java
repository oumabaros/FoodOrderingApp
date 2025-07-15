package com.pm.authservice.service;

import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.exception.Auth0IdAlreadyExistsException;
import com.pm.authservice.kafka.KafkaProducer;
import com.pm.authservice.mapper.UserMapper;
import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final KafkaProducer kafkaProducer;

  public UserService(UserRepository userRepository,KafkaProducer kafkaProducer) {
    this.userRepository = userRepository;
    this.kafkaProducer = kafkaProducer;
  }
  

  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    if (userRepository.existsByAuth0Id(userRequestDTO.getAuth0Id())) {
      throw new Auth0IdAlreadyExistsException(
              "A user with this Auth0Id " + "already exists"
                      + userRequestDTO.getAuth0Id());
    }

    User newUser = userRepository.save(
            UserMapper.toModel(userRequestDTO));

       kafkaProducer.sendEvent(newUser);

    return UserMapper.toDTO(newUser);
  }
}
