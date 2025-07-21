package com.pm.authservice.controller;

import com.pm.authservice.dto.UserReqDTO;
import com.pm.authservice.dto.UserRequestDTO;
import com.pm.authservice.dto.UserResDTO;
import com.pm.authservice.dto.UserResponseDTO;
import com.pm.authservice.dto.validators.CreateUserValidationGroup;
import com.pm.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my/user")
@Tag(name = "User", description = "API for managing Users")
public class MyUserController {

    private final UserService userService;

    public MyUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get Current User")
    public ResponseEntity<UserResponseDTO> getUser(Authentication authentication) {
        UserResponseDTO userResponseDTO = userService.getUser(authentication);
        if (userResponseDTO==null){
            return ResponseEntity.status(404).body(userResponseDTO);
        }
        return ResponseEntity.ok().body(userResponseDTO);
    }
    @PostMapping
    @Operation(summary = "Create a new user.")
    public ResponseEntity<UserResponseDTO> createUser(
            @Validated({Default.class, CreateUserValidationGroup.class})
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.createUser(
                userRequestDTO);
        if (userResponseDTO==null){
            return ResponseEntity.status(200).body(userResponseDTO);
        }

        return ResponseEntity.status(201).body(userResponseDTO);
    }

    @PutMapping
    @Operation(summary = "Update Current User.")
    public ResponseEntity<UserResDTO> updateCurrentUser(Authentication authentication,
                                                        @Validated({Default.class, CreateUserValidationGroup.class})
                                                             @RequestBody UserReqDTO userRequestDTO) {
        System.out.println("USER REQUEST: "+userRequestDTO);

        UserResDTO userResDTO = userService.updateUser(authentication,
                userRequestDTO);
        if (userResDTO==null){
            return ResponseEntity.status(404).body(userResDTO);
        }
        return ResponseEntity.ok().body(userResDTO);
    }
}