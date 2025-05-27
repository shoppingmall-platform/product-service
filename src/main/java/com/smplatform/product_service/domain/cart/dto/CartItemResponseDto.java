package com.smplatform.product_service.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class CartItemResponseDto {
    @Getter
    @AllArgsConstructor
    public static class CartGet {
        private List<CartItemResponseDto.CartItemGet> cartItems;
    }

    @Getter
    @Builder
    public static class CartItemGet {
        private Long cartItemId;
        private CartItemResponseDto.ProductOptionInfo productOptionInfo;
        private Integer quantity;
    }

    @Getter
    @Builder
    public static class ProductOptionInfo {
        private Long productOptionId;
        private CartItemResponseDto.ProductInfo productInfo;
    }

    @Getter
    @Builder
    public static class ProductInfo {
        private Long productId;
        private String name;
        private Integer price;
        private Integer discountedPrice;
        private List<CartItemResponseDto.ProductOptionGet> productOptions;
        private String thumbnailPath;
    }

    @Getter
    @Builder
    public static class ProductOptionGet {
        private Long productOptionId;
        private String productOptionName;
        private List<CartItemResponseDto.ProductOptionDetailGet> productOptionDetails;
        private Integer stockQuantity;
        private Integer additionalPrice;
    }

    @Getter
    @Builder
    public static class ProductOptionDetailGet {
        private String productOptionType;
        private String productOptionDetailName;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class CartItemFlatDto {
        private Long cartItemId;
        private Integer quantity;

        private Long productOptionId;
        private String productOptionName;
        private Integer stockQuantity;
        private Integer additionalPrice;

        private String productOptionType;
        private String productOptionDetailName;

        private Long productId;
        private String name;
        private Integer price;
        private String thumbnailPath;
    }
}
