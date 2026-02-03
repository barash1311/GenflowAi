package com.genflow.genflowai.service;

import java.util.UUID;

import com.genflow.genflowai.dto.ModelRequest;
import com.genflow.genflowai.dto.ModelResponse;
import com.genflow.genflowai.dto.PageResponse;

public interface ModelService {
    ModelResponse createModel(ModelRequest request);
    // ADMIN only

    ModelResponse getModelById(UUID modelId);

    PageResponse<ModelResponse> getAllModels(int page, int size);

    ModelResponse updateModel(UUID modelId, ModelRequest request);
    // ADMIN only

    void deleteModel(UUID modelId);
    // ADMIN only
}
