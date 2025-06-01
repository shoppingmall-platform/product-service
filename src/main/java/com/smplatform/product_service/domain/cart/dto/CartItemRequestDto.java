package com.smplatform.product_service.domain.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class CartItemRequestDto {

    @Getter
    public static class CartAdd {
        @NotNull(message = "productId를 입력해 주세요")
        private Long productOptionId;
        @Min(value = 1,message = "1개 이상만 설정이 가능합니다")
        private Integer quantity;
    }

    @Getter
    public static class CartUpdate {
        @NotNull(message = "cartItemId를 입력해 주세요")
        private Long cartItemId;
        @NotNull(message = "productOptionId를 입력해 주세요")
        private Long productOptionId;
        @Min(value = 1,message = "1개 이상만 설정이 가능합니다")
        private Integer quantity;
    }

    @Getter
    public static class CartDelete {
        @NotNull
        private Long cartItemId;
    }
}
