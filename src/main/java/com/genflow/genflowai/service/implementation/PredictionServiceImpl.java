package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.*;
import com.genflow.genflowai.entity.*;
import com.genflow.genflowai.entity.enums.Status;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.*;
import com.genflow.genflowai.service.PredictionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final PredictionJobRepository predictionJobRepository;
    private final PromptRepository promptRepository;
    private final ModelRepository modelRepository;

    public PredictionServiceImpl(
            PredictionRepository predictionRepository,
            PredictionJobRepository predictionJobRepository,
            PromptRepository promptRepository,
            ModelRepository modelRepository) {

        this.predictionRepository = predictionRepository;
        this.predictionJobRepository = predictionJobRepository;
        this.promptRepository = promptRepository;
        this.modelRepository = modelRepository;
    }

    // ================= SUBMIT ================

    @Override
    public PredictionJobResponse submitPrediction(PredictionRequest request) {
        Prompt prompt = promptRepository.findById(request.getPromptId())
                .orElseThrow(() -> new ResourceNotFoundException("Prompt not found"));

        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        PredictionJob job = new PredictionJob();
        job.setPrompt(prompt);
        job.setStatus(Status.PENDING);

        PredictionJob savedJob = predictionJobRepository.save(job);
        return mapJobResponse(savedJob);
    }

    // ================= GET PREDICTION ================

    @Override
    public PredictionResponse getPredictionById(UUID predictionId) {
        Prediction prediction = predictionRepository.findById(predictionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
        return mapPredictionResponse(prediction);
    }

    // ================= GET ALL ================

    @Override
    public PageResponse<PredictionResponse> getAllPredictions(int page, int size) {
        Page<Prediction> predictions =
                predictionRepository.findAll(PageRequest.of(page, size));

        return new PageResponse<>(
                predictions.getContent()
                        .stream()
                        .map(this::mapPredictionResponse)
                        .collect(Collectors.toList()),
                predictions.getNumber(),
                predictions.getSize(),
                predictions.getTotalElements(),
                predictions.getTotalPages()
        );
    }

    // ================= GET BY PROMPT ================

    @Override
    public PageResponse<PredictionResponse> getPredictionsByPrompt(
            UUID promptId, int page, int size) {

        Page<Prediction> predictions =
                predictionRepository.findByPrompt_Id(
                        promptId, PageRequest.of(page, size));

        return new PageResponse<>(
                predictions.getContent()
                        .stream()
                        .map(this::mapPredictionResponse)
                        .collect(Collectors.toList()),
                predictions.getNumber(),
                predictions.getSize(),
                predictions.getTotalElements(),
                predictions.getTotalPages()
        );
    }

    // ================= JOB STATUS ================

    @Override
    public PredictionJobResponse getJobStatus(UUID jobId) {
        PredictionJob job = predictionJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return mapJobResponse(job);
    }

    // ================= MAPPERS ================

    private PredictionResponse mapPredictionResponse(Prediction prediction) {
        return new PredictionResponse(
                prediction.getId(),
                prediction.getPrompt().getId(),
                prediction.getModel().getId(),
                prediction.getResult(),
                prediction.getStatus(),
                prediction.getExecutionTimeMs(),
                prediction.getCreatedAt()
        );
    }

    private PredictionJobResponse mapJobResponse(PredictionJob job) {
        return new PredictionJobResponse(
                job.getId(),
                job.getPrompt().getId(),
                job.getStatus().name(),
                job.getStartedAt(),
                job.getFinishedAt()
        );
    }
}