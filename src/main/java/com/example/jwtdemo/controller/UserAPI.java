package com.example.jwtdemo.controller;

import com.example.jwtdemo.entity.UserEntity;
import com.example.jwtdemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    private final UserRepository repo;

    public UserAPI(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());

        UserEntity newUser = new UserEntity(user.getEmail(), password);
        UserEntity savedUser = repo.save(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return repo.findAll();
    }
}
