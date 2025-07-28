package com.pm.authservice.service;

import com.pm.authservice.dto.UpdateUserRequestDTO;
import com.pm.authservice.dto.CreateUserRequestDTO;
import com.pm.authservice.dto.UpdateUserResponseDTO;
import com.pm.authservice.dto.CreateUserResponseDTO;
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

  public CreateUserResponseDTO getUser(Authentication authentication) {
    String auth0Id=getAuthId(authentication);
    if (auth0Id==null) {
      return null;
    }
    User user = userRepository.findByAuth0Id(auth0Id).orElseThrow(
            () -> new UserNotFoundException("User not found with Auth0Id: " + auth0Id));

    return UserMapper.toDTO(user);
  }
  public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO,
                                    Authentication authentication) {
    if (userRepository.existsByAuth0Id(getAuthId(authentication))) {
      return null;
    }

    User newUser = userRepository.save(UserMapper.toModel(createUserRequestDTO));
    kafkaProducer.sendEvent(newUser);

    return UserMapper.toDTO(newUser);
  }

  public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {


      User user = userRepository.findByEmail(updateUserRequestDTO.getEmail()).orElseThrow(
              () -> new UserNotFoundException("User not found with email: " + updateUserRequestDTO.getEmail()));
      if(user!=null){
        user.setName(updateUserRequestDTO.getName());
        user.setAddressLine1(updateUserRequestDTO.getAddressLine1());
        user.setCountry(updateUserRequestDTO.getCountry());
        user.setCity(updateUserRequestDTO.getCity());

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