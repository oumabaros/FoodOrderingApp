package com.pm.authservice.controller;

import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.dto.validators.CreateUserValidationGroup;
import com.pm.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User", description = "API for managing Users")
public class MyUserController {

    private final UserService user;

    public MyUserController(UserService user) {
        this.user = user;
    }
    @RequestMapping(value = "/my/user", method = RequestMethod.POST)
    @Operation(summary = "Create a new user.")
    public ResponseEntity<UserResponseDTO> createRestaurant(
            @Validated({Default.class, CreateUserValidationGroup.class})
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = user.createUser(
                userRequestDTO);

        return ResponseEntity.ok().body(userResponseDTO);
    }
}
