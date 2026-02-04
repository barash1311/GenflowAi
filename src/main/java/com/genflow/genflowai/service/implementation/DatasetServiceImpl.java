package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.DatasetRequest;
import com.genflow.genflowai.dto.DatasetResponse;
import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.entity.Dataset;
import com.genflow.genflowai.entity.User;
import com.genflow.genflowai.exceptions.ResourceNotFoundException;
import com.genflow.genflowai.repository.DatasetRepository;
import com.genflow.genflowai.repository.UserRepository;
import com.genflow.genflowai.service.DatasetService;

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
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;

    // ================= CREATE ================

    @Override
    public DatasetResponse createDataset(DatasetRequest request) {
        User currentUser = getCurrentUser();

        Dataset dataset = new Dataset();
        dataset.setName(request.getName());
        dataset.setDescription(request.getDescription());
        dataset.setSource(request.getSource());
        dataset.setRowCount(request.getRowCount());
        dataset.setUploadedBy(currentUser);

        Dataset saved = datasetRepository.save(dataset);
        return mapToResponse(saved);
    }

    // ================= GET BY ID ================

    @Override
    public DatasetResponse getDatasetById(UUID datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found"));
        return mapToResponse(dataset);
    }

    // ================= GET ALL ================

    @Override
    public PageResponse<DatasetResponse> getAllDatasets(int page, int size) {
        Page<Dataset> datasets = datasetRepository.findAll(PageRequest.of(page, size));

        return new PageResponse<>(
                datasets.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()),
                datasets.getNumber(),
                datasets.getSize(),
                datasets.getTotalElements(),
                datasets.getTotalPages()
        );
    }

    // ================= UPDATE ================

    @Override
    public DatasetResponse updateDataset(UUID datasetId, DatasetRequest request) {
        Dataset dataset = datasetRepository.findById(datasetId)
                .orElseThrow(() -> new ResourceNotFoundException("Dataset not found"));

        dataset.setName(request.getName());
        dataset.setDescription(request.getDescription());
        dataset.setSource(request.getSource());
        dataset.setRowCount(request.getRowCount());

        Dataset updated = datasetRepository.save(dataset);
        return mapToResponse(updated);
    }

    // ================= DELETE ================

    @Override
    public void deleteDataset(UUID datasetId) {
        if (!datasetRepository.existsById(datasetId)) {
            throw new ResourceNotFoundException("Dataset not found");
        }
        datasetRepository.deleteById(datasetId);
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

    private DatasetResponse mapToResponse(Dataset dataset) {
        return new DatasetResponse(
                dataset.getId(),
                dataset.getName(),
                dataset.getSource(),
                dataset.getDescription(),
                dataset.getRowCount(),
                dataset.getUploadedBy().getId(),
                dataset.getCreatedAt()
        );
    }
}
