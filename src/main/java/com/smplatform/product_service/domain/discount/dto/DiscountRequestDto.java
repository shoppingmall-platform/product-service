package com.smplatform.product_service.domain.discount.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountRequestDto {

    @Getter
    @Builder
    @ToString
    public static class DiscountRegister {

        private String discountName;
        private String discountType;
        private int discountValue;
        private LocalDateTime discountStartDate;
        private LocalDateTime discountEndDate;
        private int applyType;
        private List<Long> ids;

        public Discount toEntity() {
            return Discount.builder()
                    .discountName(this.discountName)
                    .discountType(Discount.Type.fromDescription(this.discountType)) // Enum 변환
                    .discountValue(this.discountValue)
                    .discountStartDate(this.discountStartDate)
                    .discountEndDate(this.discountEndDate)
                    .build();
        }
    }

    @Getter
    public static class UpdateDiscount {

        private int discountId;

        private String discountName;

        private double discountPercentage;

        private int discountPrice;

        private LocalDateTime discountStartDate;

        private LocalDateTime discountEndDate;

    }

    @Getter
    public static class DeleteDiscount {

        private int discountId;

    }
}
