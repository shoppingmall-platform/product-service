package com.smplatform.product_service.domain.discount.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.*;

import java.time.LocalDateTime;

public class DiscountRequestDto {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterDiscount {

        private int discountId;

        private String discountName;

        private double discountPercentage;

        private int discountPrice;

        private LocalDateTime discountStartDate;

        private LocalDateTime discountEndDate;

        public Discount toEntity() {
            return Discount.builder()
                    .discountId(this.discountId)
                    .discountName(this.discountName)
                    .discountPercentage(this.discountPercentage)
                    .discountPrice(this.discountPrice)
                    .discountStartDate(this.discountStartDate)
                    .discountEndDate(this.discountEndDate)
                    .build();
        }
    }
}
