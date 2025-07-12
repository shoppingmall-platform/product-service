package com.smplatform.product_service.domain.order.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class OrderResponseDto {
    @Getter
    public static class ProductOptionFlatDto {
        private final Long productOptionId;
        private final String productOptionName;
        private final int stockQuantity;
        private final int additionalPrice;

        private final Long productId;
        private final String name;
        private final boolean isDeleted;
        private final boolean isSelling;
        private final int price;

        private final Integer discountId;
        private final Discount.Type discountType;
        private final int discountValue;
        private final LocalDateTime discountStartDate;
        private final LocalDateTime discountEndDate;
        @Setter
        private Integer unitTotalPrice;

        public ProductOptionFlatDto(Long productOptionId, String productOptionName, int stockQuantity, int additionalPrice,
                                    Long productId, String name, boolean isDeleted, boolean isSelling, int price,
                                    Integer discountId, Discount.Type discountType, int discountValue,
                                    LocalDateTime discountStartDate, LocalDateTime discountEndDate) {
            this.productOptionId = productOptionId;
            this.productOptionName = productOptionName;
            this.stockQuantity = stockQuantity;
            this.additionalPrice = additionalPrice;
            this.productId = productId;
            this.name = name;
            this.isDeleted = isDeleted;
            this.isSelling = isSelling;
            this.price = price;
            this.discountId = discountId;
            this.discountType = discountType;
            this.discountValue = discountValue;
            this.discountStartDate = discountStartDate;
            this.discountEndDate = discountEndDate;
        }
    }
}
