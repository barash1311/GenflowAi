package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromptResponse {
    private UUID id;
    private UUID userId;
    private String userEmail;
    private UUID datasetId;
    private String datasetName;
    private String promptText;
    private LocalDateTime createdAt;
}
