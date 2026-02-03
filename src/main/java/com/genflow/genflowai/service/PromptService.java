package com.genflow.genflowai.service;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PromptRequest;
import com.genflow.genflowai.dto.PromptResponse;

import java.util.UUID;

public interface PromptService {

    PromptResponse createPrompt(PromptRequest request);
    // Stores prompt + links user + dataset

    PromptResponse getPromptById(UUID promptId);

    PageResponse<PromptResponse> getAllPrompts(int page, int size);
    // ADMIN: all, USER: own prompts (later via security)

    PageResponse<PromptResponse> getPromptsByDataset(UUID datasetId, int page, int size);

    void deletePrompt(UUID promptId);
}
