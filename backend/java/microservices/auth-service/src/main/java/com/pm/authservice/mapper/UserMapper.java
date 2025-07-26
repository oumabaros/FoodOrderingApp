package com.pm.authservice.mapper;


import com.pm.authservice.dto.CreateUserRequestDTO;
import com.pm.authservice.dto.CreateUserResponseDTO;
import com.pm.authservice.model.User;

public class UserMapper {
  public static CreateUserResponseDTO toDTO(User user) {
    CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO();
    createUserResponseDTO.setName(user.getName());
    createUserResponseDTO.setAddressLine1(user.getAddressLine1());
    createUserResponseDTO.setEmail(user.getEmail());
    createUserResponseDTO.setCity(user.getCity());
    createUserResponseDTO.setAuth0Id(user.getAuth0Id());
    createUserResponseDTO.setCountry(user.getCountry());
    return createUserResponseDTO;
  }

  public static User toModel(CreateUserRequestDTO createUserRequestDTO) {
    User user = new User();
    user.setName(createUserRequestDTO.getName());
    user.setAddressLine1(createUserRequestDTO.getAddressLine1());
    user.setEmail(createUserRequestDTO.getEmail());
    user.setAuth0Id(createUserRequestDTO.getAuth0Id());
    user.setCity(createUserRequestDTO.getCity());
    user.setCountry(createUserRequestDTO.getCountry());
    return user;
  }
}
