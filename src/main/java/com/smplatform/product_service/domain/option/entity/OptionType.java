package com.smplatform.product_service.domain.option.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "option_type")
public class OptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionTypeId;

    @Column(name = "option_type_name")
    private String optionTypeName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
