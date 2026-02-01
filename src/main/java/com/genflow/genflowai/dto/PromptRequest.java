package com.genflow.genflowai.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PromptRequest {
    private UUID datasetId;
    private String promptText;
}
