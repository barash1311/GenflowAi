package com.genflow.genflowai.dto;

import lombok.Data;

@Data
public class DatasetRequest {
    private String name;
    private String source;
    private String description;
    private Integer rowCount;
}
