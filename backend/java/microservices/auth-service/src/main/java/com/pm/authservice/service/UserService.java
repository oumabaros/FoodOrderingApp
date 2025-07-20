package com.pm.authservice.service;

import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.exception.Auth0IdAlreadyExistsException;
import com.pm.authservice.exception.UserNotFoundException;
import com.pm.authservice.kafka.KafkaProducer;
import com.pm.authservice.mapper.UserMapper;
import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import com.pm.authservice.utils.AuthUtils;
import org.springframework.security.core.Authentication;
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
      //throw new Auth0IdAlreadyExistsException("User already exists");
      return null;
    }

    User newUser = userRepository.save(UserMapper.toModel(userRequestDTO));
    kafkaProducer.sendEvent(newUser);

    return UserMapper.toDTO(newUser);
  }

  public UserResponseDTO updateUser(Authentication authentication,
                                                UserRequestDTO userRequestDTO) {
    User updatedUser;
    String auth0Id=getAuthId(authentication);
    if(auth0Id!=null){
      User user = userRepository.findByAuth0Id(auth0Id).orElseThrow(
              () -> new UserNotFoundException("User not found with Auth0Id: " + auth0Id));

      user.setName(userRequestDTO.getName());
      user.setAddressLine1(userRequestDTO.getAddressLine1());
      user.setCountry(userRequestDTO.getCountry());
      user.setCity(userRequestDTO.getCity());

      updatedUser = userRepository.save(user);
    }
    else{
      throw new UserNotFoundException("Auth0Id is null or invalid.");
    }

    return UserMapper.toDTO(updatedUser);
  }

  public String getAuthId(Authentication authentication) {
    String authId;
    if(AuthUtils.getAuthId(authentication)!=null){
      authId = AuthUtils.getAuthId(authentication);
    }
    else{
      return null;
    }
    return authId;
  }
}
