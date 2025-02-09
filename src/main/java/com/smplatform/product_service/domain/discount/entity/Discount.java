package com.smplatform.product_service.domain.discount.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    @Column(name = "discount_name")
    private String name;
    @Column(name = "discount_percentage")
    private double discountPercentage;
    @Column(name = "discount_price")
    private int discountPrice;
    @Column(name = "discount_start_date")
    private LocalDateTime discountStartDate;
    @Column(name = "discount_end_date")
    private LocalDateTime discountEndDate;
}
