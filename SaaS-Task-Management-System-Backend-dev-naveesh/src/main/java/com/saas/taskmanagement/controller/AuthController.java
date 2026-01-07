package com.saas.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.taskmanagement.model.Role;
import com.saas.taskmanagement.model.User;
import com.saas.taskmanagement.repository.UserRepo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setRole(Role.USER);
        user.setStatus(true);
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setUsername(user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }

}
