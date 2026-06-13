package com.ayush.settleUp.service;

import com.ayush.settleUp.dto.LoginRequest;
import com.ayush.settleUp.dto.RegisterRequest;


public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest request);
}
