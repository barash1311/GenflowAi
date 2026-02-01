package com.genflow.genflowai.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.DatasetRequest;
import com.genflow.genflowai.dto.DatasetResponse;
import com.genflow.genflowai.dto.PageResponse;

@RestController
@RequestMapping("/api/v1/datasets")
public class DatasetController {

    @PostMapping
    public ResponseEntity<DatasetResponse> createDataset(
            @RequestBody DatasetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<DatasetResponse>> getAllDatasets(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatasetResponse> getDataset(@PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }
}