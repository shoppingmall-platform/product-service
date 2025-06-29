package com.smplatform.product_service.domain.product.entity;

import com.smplatform.product_service.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category_mappings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public ProductCategoryMapping(int categoryId, long product) {
        this.category = Category.builder()
                .categoryId(categoryId)
                .build();
        this.product = Product.builder()
                .productId(product)
                .build();
    }
}
