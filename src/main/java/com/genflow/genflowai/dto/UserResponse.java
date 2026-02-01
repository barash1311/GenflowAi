package com.genflow.genflowai.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
import com.genflow.genflowai.entity.enums.Role;
@Data
public class UserResponse {
    private UUID id;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}