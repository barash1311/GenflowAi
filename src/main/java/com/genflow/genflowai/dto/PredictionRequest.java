package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRequest {
    private UUID promptId;
    private UUID modelId;
}
