package com.smplatform.product_service.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.option.entity.ProductOption;
import com.smplatform.product_service.domain.option.entity.ProductOptionDetail;
import com.smplatform.product_service.domain.product.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProduct {
        int id;
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
        private int discountedPrice;
        private List<ProductResponseDto.GetProductOption> productOptions;

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
                    .discountedPrice(product.getDiscountedPrice())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class GetProductOption {
        private String productOptionName;
        private List<ProductResponseDto.GetProductOptionDetail> productOptionDetails;
        private int stockQuantity;
        private int additionalPrice;

        public static ProductResponseDto.GetProductOption of(ProductOption productOption) {
            return GetProductOption.builder()
                    .productOptionName(productOption.getProductOptionName())
                    .stockQuantity(productOption.getStockQuantity())
                    .additionalPrice(productOption.getAdditionalPrice())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class GetProductOptionDetail {
        private String productOptionType;
        private String productOptionDetailName;

        public static ProductResponseDto.GetProductOptionDetail of(ProductOptionDetail productOptionDetail) {
            return GetProductOptionDetail.builder()
                    .productOptionType(productOptionDetail.getProductOptionType())
                    .productOptionDetailName(productOptionDetail.getProductOptionDetailName())
                    .build();
        }
    }
}
