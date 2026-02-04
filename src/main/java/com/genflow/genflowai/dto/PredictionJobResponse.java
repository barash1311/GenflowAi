package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionJobResponse {
    private UUID id;
    private UUID promptId;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
