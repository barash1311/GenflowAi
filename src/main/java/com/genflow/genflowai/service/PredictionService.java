package com.genflow.genflowai.service;

import java.util.UUID;

import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.dto.PredictionJobResponse;
import com.genflow.genflowai.dto.PredictionRequest;
import com.genflow.genflowai.dto.PredictionResponse;

public interface PredictionService {
    PredictionJobResponse submitPrediction(PredictionRequest request);
    // Creates job + stores prompt reference

    PredictionResponse getPredictionById(UUID predictionId);

    PageResponse<PredictionResponse> getAllPredictions(int page, int size);
    // ADMIN: all, USER: own predictions

    PageResponse<PredictionResponse> getPredictionsByPrompt(UUID promptId, int page, int size);

    PredictionJobResponse getJobStatus(UUID jobId);
}
