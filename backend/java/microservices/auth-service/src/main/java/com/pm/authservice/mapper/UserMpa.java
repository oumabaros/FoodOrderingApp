package com.pm.authservice.mapper;

import com.pm.authservice.dto.UpdateUserRequestDTO;
import com.pm.authservice.dto.UpdateUserResponseDTO;
import com.pm.authservice.model.User;

public class UserMpa {
    public static UpdateUserResponseDTO toDTO(User user) {
        UpdateUserResponseDTO userDTO = new UpdateUserResponseDTO();
        userDTO.setName(user.getName());
        userDTO.setAddressLine1(user.getAddressLine1());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setCountry(user.getCountry());
        return userDTO;
    }

    public static User toModel(UpdateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setName(createUserRequestDTO.getName());
        user.setAddressLine1(createUserRequestDTO.getAddressLine1());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setCity(createUserRequestDTO.getCity());
        user.setCountry(createUserRequestDTO.getCountry());
        return user;
    }
}
