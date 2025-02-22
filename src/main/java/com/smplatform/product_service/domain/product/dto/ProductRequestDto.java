package com.smplatform.product_service.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.product.domain.Product;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ProductRequestDto {

    @Getter
    public static class SaveProduct {
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
                    .isSelling(this.isSelling())
                    .createdAt(this.getCreatedAt())
                    .price(this.getPrice())
                    .summaryDescription(this.getSummaryDescription())
                    .simpleDescription(this.getSimpleDescription())
                    .build();
        }
    }

    @Getter
    @ToString
    public static class UpdateProduct {
        private int id;
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
    }
}
