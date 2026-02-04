package com.genflow.genflowai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatasetResponse {
    private UUID id;
    private String name;
    private String source;
    private String description;
    private Integer rowCount;
    private UUID uploadedBy;
    private LocalDateTime createdAt;
}
