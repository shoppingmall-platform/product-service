package com.smplatform.product_service.domain.order.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class OrderResponseDto {
    @Getter
    @AllArgsConstructor
    public static class ProductOptionFlatDto {
        private Long productOptionId;
        private String productOptionName;
        private int stockQuantity;
        private int additionalPrice;

        private Long productId;
        private String name;
        private boolean isDeleted;
        private boolean isSelling;
        private int price;

        private Integer discountId;
        private Discount.Type dicountType;
        private Integer discountValue;
        private LocalDateTime discountStartDate;
        private LocalDateTime discountEndDate;
    }
}
