package com.genflow.genflowai.repository;

import com.genflow.genflowai.entity.Prompt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PromptRepository extends JpaRepository<Prompt, UUID> {

    Page<Prompt> findByDataset_Id(UUID datasetId, Pageable pageable);
}