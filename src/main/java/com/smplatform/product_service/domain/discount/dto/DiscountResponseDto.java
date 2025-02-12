package com.smplatform.product_service.domain.discount.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.*;

import java.time.LocalDateTime;

public class DiscountResponseDto {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscountInfo {

        private int discountId;

        private String discountName;

        private double discountPercentage;

        private int discountPrice;

        private LocalDateTime discountStartDate;

        private LocalDateTime discountEndDate;

        public static DiscountResponseDto.DiscountInfo of(Discount entity) {
            return new DiscountResponseDto.DiscountInfo(
                    entity.getDiscountId(),
                    entity.getDiscountName(),
                    entity.getDiscountPercentage(),
                    entity.getDiscountPrice(),
                    entity.getDiscountStartDate(),
                    entity.getDiscountEndDate()
            );
        }
    }


}
