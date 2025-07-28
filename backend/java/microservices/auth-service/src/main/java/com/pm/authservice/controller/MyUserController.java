package com.pm.authservice.controller;

import com.pm.authservice.dto.UpdateUserRequestDTO;
import com.pm.authservice.dto.CreateUserRequestDTO;
import com.pm.authservice.dto.UpdateUserResponseDTO;
import com.pm.authservice.dto.CreateUserResponseDTO;
import com.pm.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CreateUserResponseDTO> getUser(Authentication authentication) {
        CreateUserResponseDTO createUserResponseDTO = userService.getUser(authentication);
        if (createUserResponseDTO==null){
            return ResponseEntity.status(404).body(createUserResponseDTO);
        }
        return ResponseEntity.ok().body(createUserResponseDTO);
    }
    @PostMapping
    @Operation(summary = "Create a new user.")
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO,
                                                      Authentication authentication) {

        CreateUserResponseDTO createUserResponseDTO = userService.createUser(createUserRequestDTO,authentication);
        if (createUserResponseDTO==null){
            return ResponseEntity.status(200).body(createUserResponseDTO);
        }

        return ResponseEntity.status(201).body(createUserResponseDTO);
    }

    @PutMapping
    @Operation(summary = "Update Current User.")
    public ResponseEntity<UpdateUserResponseDTO> updateCurrentUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        UpdateUserResponseDTO updateUserResponseDTO = userService.updateUser(updateUserRequestDTO);
        if (updateUserResponseDTO==null){
            return ResponseEntity.status(404).body(updateUserResponseDTO);
        }
        return ResponseEntity.ok().body(updateUserResponseDTO);
    }
}