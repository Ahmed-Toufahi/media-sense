package com.ahmed.media_sense_api.controller;

import com.ahmed.media_sense_api.dto.AuthResponse;
import com.ahmed.media_sense_api.dto.RegisterRequest;
import com.ahmed.media_sense_api.dto.UserDTO;
import com.ahmed.media_sense_api.model.CustomUserDetails;
import com.ahmed.media_sense_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = userService.registerUser(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getAuthenticatedUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserDTO user = userService.getUserProfile(userDetails);
        return ResponseEntity.ok(user);
    }

}