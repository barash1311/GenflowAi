package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PromptRequest;
import com.genflow.genflowai.dto.PromptResponse;

import com.genflow.genflowai.repository.DatasetRepository;
import com.genflow.genflowai.repository.PromptRepository;
import com.genflow.genflowai.service.PromptService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository promptRepository;
    private final DatasetRepository datasetRepository;

    

    @Override
    public PromptResponse createPrompt(PromptRequest request) {
        // TODO
        // 1. Fetch dataset
        // 2. Get current user from security context
        // 3. Map PromptRequest -> Prompt
        // 4. Save prompt
        // 5. Map to PromptResponse
        return null;
    }

    @Override
    public PromptResponse getPromptById(UUID promptId) {
        // TODO
        // 1. Fetch prompt
        // 2. Map to PromptResponse
        return null;
    }

    @Override
    public PageResponse<PromptResponse> getAllPrompts(int page, int size) {
        // TODO
        // 1. Pageable fetch
        // 2. Apply role-based filtering later
        // 3. Map to PromptResponse
        return null;
    }

    @Override
    public PageResponse<PromptResponse> getPromptsByDataset(UUID datasetId, int page, int size) {
        // TODO
        // 1. Validate dataset
        // 2. Fetch prompts by dataset
        // 3. Map to PromptResponse
        return null;
    }

    @Override
    public void deletePrompt(UUID promptId) {
        // TODO
        // 1. Validate ownership / admin
        // 2. Delete prompt
    }
}
