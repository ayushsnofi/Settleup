package com.ayush.settleUp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.settleUp.dto.UserResponse;
import com.ayush.settleUp.entity.User;
import com.ayush.settleUp.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
        Authentication authentication
    ){
        String email= authentication.getName();
        User user=userRepository.findByEmail(email).orElse(null);

        return ResponseEntity.ok(
            UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .build()
        );
    }
}
