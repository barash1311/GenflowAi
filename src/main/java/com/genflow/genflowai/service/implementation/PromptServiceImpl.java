package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PromptRequest;
import com.genflow.genflowai.dto.PromptResponse;
import com.genflow.genflowai.entity.Dataset;
import com.genflow.genflowai.entity.Prompt;
import com.genflow.genflowai.entity.User;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.DatasetRepository;
import com.genflow.genflowai.repository.PromptRepository;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.service.PromptService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository promptRepository;
    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;

    // ================= CREATE ================

    @Override
    public PromptResponse createPrompt(PromptRequest request) {
        Dataset dataset = datasetRepository.findById(request.getDatasetId())
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found"));

        User currentUser = getCurrentUser();

        Prompt prompt = new Prompt();
        prompt.setPromptText(request.getPromptText());
        prompt.setDataset(dataset);
        prompt.setUser(currentUser);

        Prompt saved = promptRepository.save(prompt);
        return mapToResponse(saved);
    }

    // ================= GET BY ID ================

    @Override
    public PromptResponse getPromptById(UUID promptId) {
        Prompt prompt = promptRepository.findById(promptId)
                .orElseThrow(() -> new ResourceNotFoundException("Prompt not found"));
        return mapToResponse(prompt);
    }

    // ================= GET ALL ================

    @Override
    public PageResponse<PromptResponse> getAllPrompts(int page, int size) {
        Page<Prompt> prompts = promptRepository.findAll(PageRequest.of(page, size));

        return new PageResponse<>(
                prompts.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()),
                prompts.getNumber(),
                prompts.getSize(),
                prompts.getTotalElements(),
                prompts.getTotalPages()
        );
    }

    // ================= GET BY DATASET ================

    @Override
    public PageResponse<PromptResponse> getPromptsByDataset(
            UUID datasetId, int page, int size) {
        Page<Prompt> prompts = promptRepository.findByDataset_Id(
                datasetId, PageRequest.of(page, size));

        return new PageResponse<>(
                prompts.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()),
                prompts.getNumber(),
                prompts.getSize(),
                prompts.getTotalElements(),
                prompts.getTotalPages()
        );
    }

    // ================= DELETE ================

    @Override
    public void deletePrompt(UUID promptId) {
        if (!promptRepository.existsById(promptId)) {
            throw new ResourceNotFoundException("Prompt not found");
        }
        promptRepository.deleteById(promptId);
    }

    // ================= HELPERS ================

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("User not authenticated");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // ================= MAPPER ================

    private PromptResponse mapToResponse(Prompt prompt) {
        return new PromptResponse(
                prompt.getId(),
                prompt.getUser().getId(),
                prompt.getUser().getEmail(),
                prompt.getDataset().getId(),
                prompt.getDataset().getName(),
                prompt.getPromptText(),
                prompt.getCreatedAt()
        );
    }
}
