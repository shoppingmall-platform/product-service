package com.smplatform.product_service.domain.option.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "option_value")
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_value_id")
    private long optionValueId;

    @Column(name = "option_value_name")
    private String optionValueName;

    @Setter
    @ManyToOne
    @JoinColumn(name = "option_type_id")
    private OptionType optionType;
}
