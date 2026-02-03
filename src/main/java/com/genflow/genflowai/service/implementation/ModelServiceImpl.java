package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.ModelRequest;
import com.genflow.genflowai.dto.ModelResponse;
import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.entity.Model;
import com.genflow.genflowai.repository.ModelRepository;
import com.genflow.genflowai.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelResponse createModel(ModelRequest request) {
        // TODO
        // 1. Map ModelRequest -> Model
        // 2. Save model
        // 3. Map to ModelResponse
        return null;
    }

    @Override
    public ModelResponse getModelById(UUID modelId) {
        // TODO
        // 1. Fetch model
        // 2. Map to ModelResponse
        return null;
    }

    @Override
    public PageResponse<ModelResponse> getAllModels(int page, int size) {
        // TODO
        // 1. Pageable fetch
        // 2. Map to ModelResponse list
        return null;
    }

    @Override
    public ModelResponse updateModel(UUID modelId, ModelRequest request) {
        // TODO
        // 1. Fetch model
        // 2. Update allowed fields
        // 3. Save
        // 4. Map to ModelResponse
        return null;
    }

    @Override
    public void deleteModel(UUID modelId) {
        // TODO
        // 1. Validate existence
        // 2. Delete model
    }
}