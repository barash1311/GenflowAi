package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.LoginRequest;
import com.genflow.genflowai.dto.LoginResponse;
import com.genflow.genflowai.dto.RegisterRequest;
import com.genflow.genflowai.dto.UserResponse;
import com.genflow.genflowai.entity.User;
import com.genflow.genflowai.entity.enums.Role;
import com.genflow.genflowai.exceptions.InvalidRequestException;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.security.JwtService;
import com.genflow.genflowai.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ================= REGISTER ================
    @Override
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidRequestException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new LoginResponse(token, mapToUserResponse(savedUser));
    }

    // ================= LOGIN ================
    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidRequestException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return new LoginResponse(token, mapToUserResponse(user));
    }

    // ================= LOGOUT ================
    @Override
    public void logout(String accessToken) {
        // Token blacklisting / revoke can be implemented later
        // For stateless JWT, no-op for now
    }

    // ================= CURRENT USER ================
    @Override
    public UserResponse getCurrentUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new InvalidRequestException("Unauthorized");
        }

        UserDetails userDetails = (UserDetails) principal;
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToUserResponse(user);
    }

    // ================= MAPPER ================
    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
