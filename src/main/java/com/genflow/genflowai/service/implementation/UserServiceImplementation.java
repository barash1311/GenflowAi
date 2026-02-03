package com.genflow.genflowai.service.implementation;


import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.UserRequest;
import com.genflow.genflowai.dto.UserResponse;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        // TODO
        // 1. Validate email uniqueness
        // 2. Encode password
        // 3. Set role
        // 4. Save user
        // 5. Map to UserResponse
        return null;
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        // TODO
        // 1. Fetch user
        // 2. Map to UserResponse
        return null;
    }

    @Override
    public PageResponse<UserResponse> getAllUsers(int page, int size) {
        // TODO
        // 1. Pageable fetch
        // 2. Map list<User> → list<UserResponse>
        // 3. Wrap in PageResponse
        return null;
    }

    @Override
    public UserResponse updateUser(UUID userId, UserRequest request) {
        // TODO
        // 1. Fetch user
        // 2. Update allowed fields only
        // 3. Save
        // 4. Map to UserResponse
        return null;
    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO
        // 1. Validate existence
        // 2. Delete user
    }
}

