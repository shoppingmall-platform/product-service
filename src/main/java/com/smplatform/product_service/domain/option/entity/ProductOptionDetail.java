package com.smplatform.product_service.domain.option.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_option_detail")
public class ProductOptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productOptionDetailId;

    @Column(name = "product_option_detail_name")
    private String productOptionDetailName;

    @Column(name = "product_option_type")
    private String productOptionType;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;
}
