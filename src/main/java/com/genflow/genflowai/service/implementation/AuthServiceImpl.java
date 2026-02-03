package com.genflow.genflowai.service.implementation;


import com.genflow.genflowai.dto.LoginRequest;
import com.genflow.genflowai.dto.LoginResponse;
import com.genflow.genflowai.dto.RegisterRequest;
import com.genflow.genflowai.dto.UserResponse;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

   private final UserRepository userRepository;


    @Override
    public LoginResponse register(RegisterRequest request) {
        // TODO
        // 1. Validate email uniqueness
        // 2. Encode password
        // 3. Save user with USER role
        // 4. Generate JWT
        // 5. Map User -> UserResponse
        // 6. Return LoginResponse
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // TODO
        // 1. Authenticate credentials
        // 2. Generate JWT
        // 3. Fetch user
        // 4. Map User -> UserResponse
        // 5. Return LoginResponse
        return null;
    }

    @Override
    public void logout(String accessToken) {
        // TODO
        // Token blacklist / revoke (future)
    }

    @Override
    public UserResponse getCurrentUser() {
        // TODO
        // Extract user from SecurityContext
        return null;
    }
}

