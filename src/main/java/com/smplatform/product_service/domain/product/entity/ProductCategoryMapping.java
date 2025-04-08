package com.smplatform.product_service.domain.product.entity;

import com.smplatform.product_service.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product_category_mappings")
@Getter
public class ProductCategoryMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_mapping_id")
    private int productCategoryMappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
