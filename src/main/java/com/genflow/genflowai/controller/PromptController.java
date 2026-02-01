package com.genflow.genflowai.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PromptRequest;
import com.genflow.genflowai.dto.PromptResponse;

@RestController
@RequestMapping("/api/v1/prompts")
public class PromptController {

    @PostMapping
    public ResponseEntity<PromptResponse> createPrompt(
            @RequestBody PromptRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<PromptResponse>> getMyPrompts(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok().build();
    }
}
