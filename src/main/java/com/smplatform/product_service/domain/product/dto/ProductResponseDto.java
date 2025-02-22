package com.smplatform.product_service.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.discount.dto.DiscountResponseDto;
import com.smplatform.product_service.domain.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterProduct {
        int productId;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProduct {
        int id;
        private String name;
        private String description;
        @JsonProperty("is_deleted")
        private boolean isDeleted;
        @JsonProperty("category_id")
        private int categoryId;
        @JsonProperty("product_state")
        private ProductState productState;
        @JsonProperty("is_selling")
        private boolean isSelling;
        @JsonProperty("created_at")
        private LocalDateTime createdAt;
        private int price;
        @JsonProperty("discount_id")
        private Integer discountId;
        @JsonProperty("summary_description")
        private String summaryDescription;
        @JsonProperty("simple_description")
        private String simpleDescription;

        public Product toEntity() {
            return Product.builder()
                    .id(0)
                    .name(this.getName())
                    .description(this.getDescription())
                    .isDeleted(this.isDeleted())
                    .productState(this.getProductState())
                    .build();
        }

        public static ProductResponseDto.GetProduct of(Product product) {
            return GetProduct.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .isDeleted(product.isDeleted())
                    .categoryId(product.getCategory().getCategoryId())
                    .productState(product.getProductState())
                    .isSelling(product.isSelling())
                    .createdAt(product.getCreatedAt())
                    .price(product.getPrice())
                    .discountId(Objects.isNull(product.getDiscount()) ? null : product.getDiscount().getDiscountId())
                    .summaryDescription(product.getSummaryDescription())
                    .simpleDescription(product.getSimpleDescription())
                    .build();
        }
    }
}
