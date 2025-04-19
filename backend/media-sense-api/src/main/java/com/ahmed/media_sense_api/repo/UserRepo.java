package com.ahmed.media_sense_api.repo;

import com.ahmed.media_sense_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsByUsername(String username);

}