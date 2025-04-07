package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Register;
import com.examly.springapp.service.UserRegisterService;


@RestController
@RequestMapping("/api")
public class UserRegisterController {
    
        @Autowired
        private UserRegisterService registerService;
    
        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@RequestBody Register user) {
            if (registerService.checkEmailExists(user.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists!");
            }
            registerService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }
       
        @PostMapping("/login")
        public ResponseEntity<String> loginUser(@RequestBody Register login) {
            boolean isAuthenticated = registerService.authenticateUser(login.getEmail(), login.getPassword());
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials!");
            }
    }
    }
    



