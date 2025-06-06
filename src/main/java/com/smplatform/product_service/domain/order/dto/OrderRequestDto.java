package com.smplatform.product_service.domain.order.dto;

import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import lombok.Getter;

import java.util.List;

public class OrderRequestDto {
    @Getter
    public static class OrderSave {
        private Long addressId;
        private List<ProductResponseDto.ProductOptionGet> products;
        private String paymentMethod;
    }

}
