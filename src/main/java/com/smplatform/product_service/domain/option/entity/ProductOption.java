package com.smplatform.product_service.domain.option.entity;

import com.smplatform.product_service.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_option")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private long productOptionId;

    @Column(name = "product_option_name")
    private String productOptionName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "additional_price")
    private int additionalPrice;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

