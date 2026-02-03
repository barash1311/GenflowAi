package com.genflow.genflowai.service;

import com.genflow.genflowai.dto.LoginRequest;
import com.genflow.genflowai.dto.LoginResponse;
import com.genflow.genflowai.dto.RegisterRequest;
import com.genflow.genflowai.dto.UserResponse;
public interface AuthService {
    LoginResponse register(RegisterRequest request);
    // Creates user + returns login response (token + user)

    LoginResponse login(LoginRequest request);
    // Authenticates user + returns token + user

    void logout(String accessToken);
    // Invalidates token (implementation later)

    UserResponse getCurrentUser();
    // Returns authenticated user details
}
