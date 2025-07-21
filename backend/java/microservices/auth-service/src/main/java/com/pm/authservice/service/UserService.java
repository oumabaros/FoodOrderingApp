package com.pm.authservice.service;

import com.pm.authservice.dto.UserReqDTO;
import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.exception.UserNotFoundException;
import com.pm.authservice.kafka.KafkaProducer;
import com.pm.authservice.mapper.UserMapper;
import com.pm.authservice.mapper.UserMpa;
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

  public UserResponseDTO getUser(Authentication authentication) {
    String auth0Id=getAuthId(authentication);
    if (auth0Id==null) {
      return null;
    }

    User user = userRepository.findByAuth0Id(auth0Id).orElseThrow(
            () -> new UserNotFoundException("User not found with Auth0Id: " + auth0Id));


    return UserMapper.toDTO(user);
  }
  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    if (userRepository.existsByAuth0Id(userRequestDTO.getAuth0Id())) {
      return null;
    }

    User newUser = userRepository.save(UserMapper.toModel(userRequestDTO));
    kafkaProducer.sendEvent(newUser);

    return UserMapper.toDTO(newUser);
  }

  public UserResDTO updateUser(Authentication authentication,
                               UserReqDTO userRequestDTO) {
    String auth0Id=getAuthId(authentication);
    if(auth0Id!=null){
      User user = userRepository.findByAuth0Id(auth0Id).orElseThrow(
              () -> new UserNotFoundException("User not found with Auth0Id: " + auth0Id));

      user.setName(userRequestDTO.getName());
      user.setAddressLine1(userRequestDTO.getAddressLine1());
      user.setCountry(userRequestDTO.getCountry());
      user.setCity(userRequestDTO.getCity());

      User updatedUser = userRepository.save(user);
      return UserMpa.toDTO(updatedUser);
    }
    else{
      return null;
    }

  }

  public String getAuthId(Authentication authentication) {
    String authId = null;
    if(authentication.isAuthenticated()){
      authId = AuthUtils.getAuthId(authentication);
    }

    return authId;
  }


}