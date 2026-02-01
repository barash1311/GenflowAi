package com.genflow.genflowai.dto;

import lombok.Data;
import com.genflow.genflowai.entity.enums.Role;

@Data
public class UserRequest {
    private String email;   
    private Role role;    
}
