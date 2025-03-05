package com.smplatform.product_service.domain.option.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "option_value")
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_value_id")
    private int optionValueId;

    @ManyToOne
    @JoinColumn(name = "parent_option_value_id")
    private OptionValue parentOptionValue;

    @ManyToOne
    @JoinColumn(name = "option_type_id")
    private OptionType optionType;
}
