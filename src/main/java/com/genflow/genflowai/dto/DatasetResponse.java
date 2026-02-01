package com.genflow.genflowai.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DatasetResponse {
    private UUID id;
    private String name;
    private String source;
    private String description;
    private Integer rowCount;
    private UUID uploadedBy;
    private LocalDateTime createdAt;
}
