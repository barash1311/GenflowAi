package com.genflow.genflowai.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prediction_jobs")
@Data
public class PredictionJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "prompt_id")
    private Prompt prompt;

    // PENDING / RUNNING / SUCCESS / FAILED
    private Status status;

    @CreationTimestamp
    private LocalDateTime startedAt;
    @UpdateTimestamp
    private LocalDateTime finishedAt;
}
