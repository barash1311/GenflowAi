package com.genflow.genflowai.repository;

import com.genflow.genflowai.entity.Prediction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PredictionRepository extends JpaRepository<Prediction, UUID> {

    Page<Prediction> findByPrompt_Id(UUID promptId, Pageable pageable);
}