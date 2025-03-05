package com.smplatform.product_service.domain.option.dto;

import com.smplatform.product_service.domain.product.entity.ProductOption;
import lombok.Getter;

import java.time.LocalDateTime;

public class ProductOptionRequestDto {
    private ProductOptionRequestDto() {
    }

    @Getter
    public static class SaveProductOption {
        private int productId;
        private int optionValueId;
        private int stockQuantity;

        public ProductOption toEntity() {
            return ProductOption.builder()
                    .createdAt(LocalDateTime.now())
                    .stockQuantity(stockQuantity)
                    .build();
        }
    }
}
