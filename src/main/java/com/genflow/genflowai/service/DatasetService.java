package com.genflow.genflowai.service;

import java.util.UUID;

import com.genflow.genflowai.dto.DatasetRequest;
import com.genflow.genflowai.dto.DatasetResponse;
import com.genflow.genflowai.dto.PageResponse;

public interface DatasetService {
    DatasetResponse createDataset(DatasetRequest request);
    // Upload metadata only (data already in DB)

    DatasetResponse getDatasetById(UUID datasetId);

    PageResponse<DatasetResponse> getAllDatasets(int page, int size);

    DatasetResponse updateDataset(UUID datasetId, DatasetRequest request);

    void deleteDataset(UUID datasetId);
}
