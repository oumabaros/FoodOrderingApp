package com.pm.authservice.mapper;

import com.pm.authservice.dto.UserReqDTO;
import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.model.User;

public class UserMpa {
    public static UserResDTO toDTO(User user) {
        UserResDTO userDTO = new UserResDTO();
        userDTO.setName(user.getName());
        userDTO.setAddressLine1(user.getAddressLine1());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setCountry(user.getCountry());
        return userDTO;
    }

    public static User toModel(UserReqDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setAddressLine1(userRequestDTO.getAddressLine1());
        user.setEmail(userRequestDTO.getEmail());
        user.setCity(userRequestDTO.getCity());
        user.setCountry(userRequestDTO.getCountry());
        return user;
    }
}
