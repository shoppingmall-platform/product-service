package com.smplatform.product_service.domain.discount.dto;

import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.category.domain.Category;
import com.smplatform.product_service.domain.discount.entity.Discount;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {

    private int discountId;

    private String discountName;

    private double discountPercentage;

    private int discountPrice;

    private LocalDateTime discountStartDate;

    private LocalDateTime discountEndDate;

    public static DiscountDto toDTO(Discount entity) {
        return DiscountDto.builder()
                .discountId(entity.getDiscountId())
                .discountName(entity.getDiscountName())
                .discountPercentage(entity.getDiscountPercentage())
                .discountPrice(entity.getDiscountPrice())
                .discountStartDate(entity.getDiscountStartDate())
                .discountEndDate(entity.getDiscountEndDate())
                .build();
    }
}
