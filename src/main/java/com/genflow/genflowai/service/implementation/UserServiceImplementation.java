package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.UserRequest;
import com.genflow.genflowai.dto.UserResponse;
import com.genflow.genflowai.entity.User;
import com.genflow.genflowai.entity.enums.Role;
import com.genflow.genflowai.exceptions.InvalidRequestException;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= CREATE ================

    @Override
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidRequestException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    // ================= GET BY ID ================

    @Override
    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToResponse(user);
    }

    // ================= GET ALL ================

    @Override
    public PageResponse<UserResponse> getAllUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));

        return new PageResponse<>(
                users.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()),
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages()
        );
    }

    // ================= UPDATE ================

    @Override
    public UserResponse updateUser(UUID userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidRequestException("Email already exists");
        }

        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        User updated = userRepository.save(user);
        return mapToResponse(updated);
    }

    // ================= DELETE ================

    @Override
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    // ================= MAPPER ================

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
