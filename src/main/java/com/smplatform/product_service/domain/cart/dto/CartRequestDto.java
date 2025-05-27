package com.smplatform.product_service.domain.cart.dto;

import lombok.Getter;

import java.util.List;

public class CartRequestDto {

    @Getter
    public static class CartAdd {
        private List<CartItemDto> items;

        @Getter
        private static class CartItemDto {
            private Long productOptionId;
            private Integer quantity;
        }
    }

    @Getter
    public static class CartUpdate {
        private Integer cartId;
        private Integer quantity;
    }

    @Getter
    public static class CartDelete {
        private Integer cartId;
    }
}
