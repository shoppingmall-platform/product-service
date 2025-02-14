package com.smplatform.product_service.domain.product.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.product.domain.Product;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductRequestDto {
    private int id;
    private String name;
    private String description;
    private boolean isDeleted;
    private Category category;
    private ProductState productState;
    private boolean isSelling;
    private LocalDateTime createdAt;
    private int price;
    private Discount discount;
    private String summaryDescription;
    private String simpleDescription;

    public Product toEntity() {
        return Product.builder()
                .id(0)
                .name(this.getName())
                .description(this.getDescription())
                .isDeleted(this.isDeleted())
                .category(this.getCategory())
                .productState(this.getProductState())
                .build();
    }

    public static ProductRequestDto of(Product product) {
        return ProductRequestDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .isDeleted(product.isDeleted())
                .category(product.getCategory())
                .productState(product.getProductState())
                .isSelling(product.isSelling())
                .createdAt(product.getCreatedAt())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .summaryDescription(product.getSummaryDescription())
                .simpleDescription(product.getSimpleDescription())
                .build();
    }
}
