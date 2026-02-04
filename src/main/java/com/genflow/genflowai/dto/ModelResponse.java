package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse {
    private UUID id;
    private String name;
    private String version;
    private String algorithm;
    private String description;
    private Float accuracy;
    private LocalDateTime createdAt;
}