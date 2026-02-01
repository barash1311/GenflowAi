package com.genflow.genflowai.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PredictionRequest {
    private UUID promptId;
    private UUID modelId;
}
