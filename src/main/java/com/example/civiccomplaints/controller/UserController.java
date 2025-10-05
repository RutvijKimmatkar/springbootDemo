package com.example.civiccomplaints.controller;

import com.example.civiccomplaints.entity.User;
import com.example.civiccomplaints.repository.UserRepository;
import com.example.civiccomplaints.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        repo.save(user);

        Map<String, Object> body = Map.of(
                "message", "User registered successfully",
                "id", user.getId(),
                "fullName", user.getFullName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<User> userOpt = repo.findByMobile(req.getMobile());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (encoder.matches(req.getPasswordHash(), user.getPasswordHash())) {
                return ResponseEntity.ok(user); // returns full User JSON
            }
        }

        Map<String, String> error = Map.of("error", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}