package com.ahmed.media_sense_api.service;

import com.ahmed.media_sense_api.model.CustomUserDetails;
import com.ahmed.media_sense_api.model.User;
import com.ahmed.media_sense_api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);

        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(username + " not found");
        }
        return new CustomUserDetails(user);
    }
}