package com.genflow.genflowai.entity;

import com.genflow.genflowai.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // ADMIN / USER
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
