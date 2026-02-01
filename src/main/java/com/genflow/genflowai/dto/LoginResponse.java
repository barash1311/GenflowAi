package com.genflow.genflowai.dto;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}