package com.smplatform.product_service.domain.cart.dto;

import lombok.Getter;

import java.util.List;

public class CartItemRequestDto {

    @Getter
    public static class CartAdd {
        private Long productOptionId;
        private Integer quantity;
    }

    @Getter
    public static class CartUpdate {
        private Long cartItemId;
        private Long productOptionId;
        private Integer quantity;
    }

    @Getter
    public static class CartDelete {
        private Integer cartId;
    }
}
