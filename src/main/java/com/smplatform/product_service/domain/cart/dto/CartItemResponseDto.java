package com.smplatform.product_service.domain.cart.dto;

import lombok.Getter;

import java.util.List;

public class CartItemResponseDto {
    @Getter
    public static class CartGet {
        private List<CartItemResponseDto.CartItem> cartItems;
    }

    @Getter
    public static class CartItem {
        private Long productId;
        private String productName;
        private Long productOptionId;
        private String productOptionName;
        private int additionalPrice;
    }
}
