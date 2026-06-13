package com.ayush.settleUp.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayush.settleUp.dto.LoginRequest;
import com.ayush.settleUp.dto.RegisterRequest;
import com.ayush.settleUp.entity.User;
import com.ayush.settleUp.repository.UserRepository;
import com.ayush.settleUp.service.AuthService;
import com.ayush.settleUp.service.security.JwtSerivce;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSerivce jwtSerivce;

    @Override
    public void register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user= User.builder()
                      .name(request.getName()).email(request.getEmail())
                      .password(passwordEncoder.encode(request.getPassword()))
                      .build();

        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request){
        User user= userRepository.findByEmail(request.getEmail())
                                 .orElseThrow(()-> new RuntimeException("Invalid Credentials"));

        boolean valid= passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!valid){
            throw new RuntimeException("Invalid Expection");
        }

        return jwtSerivce.generateToken(user.getEmail());
    }
}
