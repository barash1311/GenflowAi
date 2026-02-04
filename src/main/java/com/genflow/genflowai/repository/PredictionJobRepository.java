package com.genflow.genflowai.repository;

import com.genflow.genflowai.entity.PredictionJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PredictionJobRepository extends JpaRepository<PredictionJob, UUID> {
}