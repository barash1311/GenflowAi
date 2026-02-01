package com.genflow.genflowai.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PredictionResponse {
    private UUID id;
    private UUID promptId;
    private UUID modelId;
    private String result;
    private String status;
    private Integer executionTimeMs;
    private LocalDateTime createdAt;
}
