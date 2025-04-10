package com.ahmed.media_sense_api.service;

import com.ahmed.media_sense_api.dto.RegisterRequest;
import com.ahmed.media_sense_api.dto.UserDTO;
import com.ahmed.media_sense_api.model.CustomUserDetails;
import com.ahmed.media_sense_api.model.User;
import com.ahmed.media_sense_api.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserRepo userRepo;


    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        return jwtService.generateToken(user.getUsername());  // Return JWT after registration
    }

    public UserDTO getUserProfile(CustomUserDetails userDetails) {
        if (userDetails != null) {
            return new UserDTO(userDetails.getUsername());
        }
        throw new RuntimeException("No authenticated user found");
    }

}
