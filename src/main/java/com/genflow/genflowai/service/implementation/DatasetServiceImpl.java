package com.genflow.genflowai.service.implementation;

import com.genflow.genflowai.dto.DatasetRequest;
import com.genflow.genflowai.dto.DatasetResponse;
import com.genflow.genflowai.dto.PageResponse;
import com.genflow.genflowai.entity.Dataset;
import com.genflow.genflowai.entity.User;
import com.genflow.genflowai.repository.DatasetRepository;
import com.genflow.genflowai.service.DatasetService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;


    @Override
    public DatasetResponse createDataset(DatasetRequest request) {
        // TODO
        // 1. Map DatasetRequest -> Dataset
        // 2. Set uploadedBy from security context
        // 3. Save dataset
        // 4. Map to DatasetResponse
        return null;
    }

    @Override
    public DatasetResponse getDatasetById(UUID datasetId) {
        // TODO
        // 1. Fetch dataset
        // 2. Map to DatasetResponse
        return null;
    }

    @Override
    public PageResponse<DatasetResponse> getAllDatasets(int page, int size) {
        // TODO
        // 1. Pageable fetch
        // 2. Map to DatasetResponse list
        // 3. Wrap in PageResponse
        return null;
    }

    @Override
    public DatasetResponse updateDataset(UUID datasetId, DatasetRequest request) {
        // TODO
        // 1. Fetch dataset
        // 2. Update allowed fields
        // 3. Save
        // 4. Map to DatasetResponse
        return null;
    }

    @Override
    public void deleteDataset(UUID datasetId) {
        // TODO
        // 1. Validate existence
        // 2. Delete dataset
    }
}
