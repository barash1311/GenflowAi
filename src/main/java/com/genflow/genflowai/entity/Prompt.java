package com.genflow.genflowai.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "prompts")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dataset_id")
    private Dataset dataset;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String promptText;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
