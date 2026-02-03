package com.genflow.genflowai.service;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.UserRequest;
import com.genflow.genflowai.dto.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest request);
    // ADMIN use-case

    UserResponse getUserById(UUID userId);
    // ADMIN / SELF

    PageResponse<UserResponse> getAllUsers(int page, int size);
    // ADMIN only

    UserResponse updateUser(UUID userId, UserRequest request);
    // ADMIN / SELF (limited fields)

    void deleteUser(UUID userId);
    // ADMIN only
}