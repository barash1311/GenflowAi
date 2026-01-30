package com.genflow.genflowai.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "prompt_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_predictions_prompt")
    )
    private Prompt prompt;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "model_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_predictions_model")
    )
    private Model model;

    @Column(columnDefinition = "JSON")
    private String result;

    private String status;
    private Integer executionTimeMs;

    @CreationTimestamp
    private LocalDateTime createdAt;
}