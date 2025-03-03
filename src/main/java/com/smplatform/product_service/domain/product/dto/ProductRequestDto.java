package com.smplatform.product_service.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.product.entity.Product;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class ProductRequestDto {

    @Getter
    public static class SaveProduct {
        private String name;
        private String description;
        private boolean isDeleted;
        private int categoryId;
        private ProductState productState;
        private boolean isSelling;
        private LocalDateTime createdAt;
        private int price;
        private Integer discountId;
        private String summaryDescription;
        private String simpleDescription;
        private List<ProductOption> productOptions;

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
    public static class ProductOption {
        private String productOptionName;
        private List<ProductOptionDetail> productOptionDetails;
        private int stockQuantity;
        private int additionalPrice;
    }

    @Getter
    public static class ProductOptionDetail {
        private String optionType;
        private String optionValue;
    }

    @Getter
    @ToString
    public static class UpdateProduct {
        private int id;
        private String name;
        private String description;
        private boolean isDeleted;
        private int categoryId;
        private ProductState productState;
        private boolean isSelling;
        private LocalDateTime createdAt;
        private int price;
        private Integer discountId;
        private String summaryDescription;
        private String simpleDescription;
    }
}
