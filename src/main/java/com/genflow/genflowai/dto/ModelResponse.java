package com.genflow.genflowai.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ModelResponse {
    private UUID id;
    private String name;
    private String version;
    private String algorithm;
    private Float accuracy;
    private LocalDateTime createdAt;
}