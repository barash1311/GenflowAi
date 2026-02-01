package com.genflow.genflowai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.UserRequest;
import com.genflow.genflowai.dto.UserResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok().build();
    }
}
