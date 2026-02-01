package com.genflow.genflowai.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
}
