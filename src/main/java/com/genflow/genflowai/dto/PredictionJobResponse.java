package com.genflow.genflowai.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PredictionJobResponse {
    private UUID id;
    private UUID promptId;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
