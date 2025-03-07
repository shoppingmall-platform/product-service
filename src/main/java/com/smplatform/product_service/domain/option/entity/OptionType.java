package com.smplatform.product_service.domain.option.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "option_type")
public class OptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long optionTypeId;

    @Column(name = "option_type_name")
    private String optionTypeName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "optionType", cascade = CascadeType.ALL)
    private List<OptionValue> optionValues;
}
