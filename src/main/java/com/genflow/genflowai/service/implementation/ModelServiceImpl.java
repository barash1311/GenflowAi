package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.ModelRequest;
import com.genflow.genflowai.dto.ModelResponse;
import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.entity.Model;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.ModelRepository;
import com.genflow.genflowai.service.ModelService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    // ================= CREATE ================

    @Override
    public ModelResponse createModel(ModelRequest request) {
        Model model = new Model();
        model.setName(request.getName());
        model.setVersion(request.getVersion());
        model.setAlgorithm(request.getAlgorithm());
        model.setDescription(request.getDescription());
        model.setAccuracy(request.getAccuracy());

        Model saved = modelRepository.save(model);
        return mapToResponse(saved);
    }

    // ================= GET BY ID ================

    @Override
    public ModelResponse getModelById(UUID modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
        return mapToResponse(model);
    }

    // ================= GET ALL ================

    @Override
    public PageResponse<ModelResponse> getAllModels(int page, int size) {
        Page<Model> models = modelRepository.findAll(PageRequest.of(page, size));

        return new PageResponse<>(
                models.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()),
                models.getNumber(),
                models.getSize(),
                models.getTotalElements(),
                models.getTotalPages()
        );
    }

    // ================= UPDATE ================

    @Override
    public ModelResponse updateModel(UUID modelId, ModelRequest request) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        model.setName(request.getName());
        model.setVersion(request.getVersion());
        model.setAlgorithm(request.getAlgorithm());
        model.setDescription(request.getDescription());
        model.setAccuracy(request.getAccuracy());

        Model updated = modelRepository.save(model);
        return mapToResponse(updated);
    }

    // ================= DELETE ================

    @Override
    public void deleteModel(UUID modelId) {
        if (!modelRepository.existsById(modelId)) {
            throw new ResourceNotFoundException("Model not found");
        }
        modelRepository.deleteById(modelId);
    }

    // ================= MAPPER ================

    private ModelResponse mapToResponse(Model model) {
        return new ModelResponse(
                model.getId(),
                model.getName(),
                model.getVersion(),
                model.getAlgorithm(),
                model.getDescription(),
                model.getAccuracy(),
                model.getCreatedAt()
        );
    }
}
