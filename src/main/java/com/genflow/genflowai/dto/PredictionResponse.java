package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResponse {
    private UUID id;
    private UUID promptId;
    private UUID modelId;
    private String result;
    private String status;
    private Integer executionTimeMs;
    private LocalDateTime createdAt;
}
