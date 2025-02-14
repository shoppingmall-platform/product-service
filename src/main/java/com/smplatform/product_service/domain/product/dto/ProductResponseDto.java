package com.smplatform.product_service.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterProduct {
        int productId;
    }
}
