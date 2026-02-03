package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PredictionJobResponse;
import com.genflow.genflowai.dto.PredictionRequest;
import com.genflow.genflowai.dto.PredictionResponse;
import com.genflow.genflowai.entity.Model;
import com.genflow.genflowai.entity.Prediction;
import com.genflow.genflowai.entity.PredictionJob;
import com.genflow.genflowai.entity.Prompt;
import com.genflow.genflowai.entity.enums.Status;
import com.genflow.genflowai.repository.ModelRepository;
import com.genflow.genflowai.repository.PredictionJobRepository;
import com.genflow.genflowai.repository.PredictionRepository;
import com.genflow.genflowai.repository.PromptRepository;
import com.genflow.genflowai.service.PredictionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final PredictionJobRepository predictionJobRepository;
    private final PromptRepository promptRepository;
    private final ModelRepository modelRepository;

    public PredictionServiceImpl(PredictionRepository predictionRepository,
                                 PredictionJobRepository predictionJobRepository,
                                 PromptRepository promptRepository,
                                 ModelRepository modelRepository) {
        this.predictionRepository = predictionRepository;
        this.predictionJobRepository = predictionJobRepository;
        this.promptRepository = promptRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public PredictionJobResponse submitPrediction(PredictionRequest request) {
        // TODO
        // 1. Fetch prompt
        // 2. Fetch model
        // 3. Create PredictionJob (PENDING)
        // 4. Persist job
        // 5. Return job response
        // 6. Trigger async execution later (NOT NOW)
        return null;
    }

    @Override
    public PredictionResponse getPredictionById(UUID predictionId) {
        // TODO
        // 1. Fetch prediction
        // 2. Map to PredictionResponse
        return null;
    }

    @Override
    public PageResponse<PredictionResponse> getAllPredictions(int page, int size) {
        // TODO
        // 1. Pageable fetch
        // 2. Role-based filtering later
        // 3. Map to PredictionResponse
        return null;
    }

    @Override
    public PageResponse<PredictionResponse> getPredictionsByPrompt(UUID promptId, int page, int size) {
        // TODO
        // 1. Validate prompt
        // 2. Fetch predictions
        // 3. Map to PredictionResponse
        return null;
    }

    @Override
    public PredictionJobResponse getJobStatus(UUID jobId) {
        // TODO
        // 1. Fetch job
        // 2. Map to PredictionJobResponse
        return null;
    }
}
