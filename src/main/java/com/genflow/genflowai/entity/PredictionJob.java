package com.genflow.genflowai.entity;

import com.genflow.genflowai.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prediction_jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictionJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "prompt_id", nullable = false, unique = true)
    private Prompt prompt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime startedAt;

    @UpdateTimestamp
    private LocalDateTime finishedAt;
}
