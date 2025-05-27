package com.smplatform.product_service.domain.cart.dto;

import lombok.Getter;

public class CartRequestDto {

    @Getter
    public static class CartAdd {
        private Long productOptionId;
        private Integer quantity;
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
