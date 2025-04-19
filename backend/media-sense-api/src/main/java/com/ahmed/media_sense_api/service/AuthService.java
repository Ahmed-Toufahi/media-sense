package com.ahmed.media_sense_api.service;


import com.ahmed.media_sense_api.exception.InvalidCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

//    public String authenticate(String username, String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//            return jwtService.generateToken(username);
//        } catch (Exception e) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//    }

    public String authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return jwtService.generateToken(authentication.getName());
        } catch (BadCredentialsException e) {
            // Wrap and rethrow with your custom exception
            throw new InvalidCredentialsException("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed due to an internal error");
        }
    }

}

