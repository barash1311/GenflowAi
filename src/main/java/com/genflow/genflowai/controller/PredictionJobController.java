package com.genflow.genflowai.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.PredictionJobResponse;

@RestController
@RequestMapping("/api/v1/prediction-jobs")
public class PredictionJobController {

    @GetMapping("/{id}")
    public ResponseEntity<PredictionJobResponse> getJobStatus(
            @PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }
}
