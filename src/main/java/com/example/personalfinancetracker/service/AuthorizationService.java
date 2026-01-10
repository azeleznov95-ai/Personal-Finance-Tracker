package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.exeptions.ConflictException;
import com.example.personalfinancetracker.model.User;
import com.example.personalfinancetracker.repository.UserRepository;
import com.example.personalfinancetracker.security.JWToken;
import lombok.var;
import org.hibernate.sql.Template;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Spliterator;

@Service
public class AuthorizationService {
    private final JWToken jwToken;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    AuthorizationService(UserRepository userRepository,PasswordEncoder encoder,JWToken jwToken){
        this.userRepository=userRepository;
        this.encoder=encoder;
        this.jwToken = jwToken;

    }
    public String register(String login, String password){
        Optional<User> userRepositoryEntity =userRepository.findUserByLogin(login);
        if (userRepositoryEntity.isPresent()){
            throw new ConflictException("Login already exists");
        }
        String passwordHash = encoder.encode(password);
        User userEntity = new User();
        userEntity.setPasswordHash(passwordHash);
        login(login,password);
        return ResponseEntity.status(102).body("Processing");
    }
    public ResponseEntity<String> login(String login,String passwordHash){
        Optional<User> userEntity = userRepository.findUserByLogin(login);
        if (userEntity.isEmpty()){
            return ResponseEntity.status(500).body("Unknown Problem");
        }
        if (encoder.matches(passwordHash,userEntity.get().getPasswordHash())){
            jwToken.setLogin(login);
            jwToken.setPasswordHash(passwordHash);
            jwToken.setUserId(userEntity.get().getId());
            return ResponseEntity.status(201).body("Login was successful");
        }
        return ResponseEntity.status(401).body("Incorrect Password");
    }

}
