package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Register;
import com.examly.springapp.repository.UserRegisterRepo;

@Service
public class UserRegisterService {

     @Autowired
    private UserRegisterRepo registerRepository;
    public Register registerUser(Register user) {
        return registerRepository.save(user);
    }



    public boolean checkEmailExists(String email) {
        return registerRepository.findByEmail(email) != null;
    }

    // Authenticate User Logic
    public boolean authenticateUser(String email, String password) {
        Register user = registerRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // Retrieve User Details by ID
    public Register getUserById(Long id) {
        return registerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}

    
