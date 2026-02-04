package com.genflow.genflowai.repository;
import com.genflow.genflowai.entity.Dataset;
import com.genflow.genflowai.entity.User;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, UUID> {
    Page<Dataset> findAllByUploadedBy(User user, Pageable pageable);
}