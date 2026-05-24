package com.sharad.springsecurity.service;

import com.sharad.springsecurity.entity.UserEntity;
import com.sharad.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // user registration method
    public String userRegistration(UserEntity userEntity) {
        Optional<UserEntity> user=userRepository.findByUsername(userEntity.getUsername());
        if(user.isPresent()){
            throw new RuntimeException("User already exists");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("USER");   // default role
        userRepository.save(userEntity);

        return "User registered successfully";
    }
}
