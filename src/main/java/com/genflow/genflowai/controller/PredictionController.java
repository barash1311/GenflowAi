package com.genflow.genflowai.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.PredictionRequest;
import com.genflow.genflowai.dto.PredictionResponse;

@RestController
@RequestMapping("/api/v1/predictions")
public class PredictionController {

    @PostMapping
    public ResponseEntity<PredictionResponse> submitPrediction(
            @RequestBody PredictionRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredictionResponse> getPrediction(
            @PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }
}
