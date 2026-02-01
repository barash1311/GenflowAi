package com.genflow.genflowai.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.ModelResponse;

@RestController
@RequestMapping("/api/v1/models")
public class ModelController {

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAllModels() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getModel(@PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }
}