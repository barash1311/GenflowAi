package com.genflow.genflowai.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;


@Data
public class ModelRequest {
    private UUID id;
    private String name;
    private String version;
    private String algorithm;
    private Float accuracy;
    private LocalDateTime createdAt;
}
