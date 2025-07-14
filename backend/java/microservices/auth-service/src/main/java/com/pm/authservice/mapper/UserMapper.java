package com.pm.authservice.mapper;


import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.model.User;

import java.time.LocalDate;

public class UserMapper {
  public static UserResponseDTO toDTO(User user) {
    UserResponseDTO userDTO = new UserResponseDTO();
    userDTO.setId(user.getId().toString());
    userDTO.setName(user.getName());
    userDTO.setAddressLine1(user.getAddressLine1());
    userDTO.setEmail(user.getEmail());
    userDTO.setCity(user.getCity());
    userDTO.setAuth0Id(user.getAuth0Id());
    userDTO.setCountry(user.getCountry());
    return userDTO;
  }

  public static User toModel(UserRequestDTO userRequestDTO) {
    User user = new User();
    user.setName(userRequestDTO.getName());
    user.setAddressLine1(userRequestDTO.getAddressLine1());
    user.setEmail(userRequestDTO.getEmail());
    user.setAuth0Id(userRequestDTO.getAuth0Id());
    user.setCity(userRequestDTO.getCity());
    user.setCountry(userRequestDTO.getCountry());
    return user;
  }
}
