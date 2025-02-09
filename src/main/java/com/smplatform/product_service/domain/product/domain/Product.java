package com.smplatform.product_service.domain.product.domain;

import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.category.domain.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "product_state")
    @Enumerated(EnumType.STRING)
    private ProductState productState;

    @Column(name = "is_selling")
    private boolean isSelling;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "price")
    private int price;

    @JoinColumn(name = "discount_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Discount discount;

    @Column(name = "summary_description")
    private String summaryDescription;

    @Column(name = "simple_description")
    private String simpleDescription;
}
