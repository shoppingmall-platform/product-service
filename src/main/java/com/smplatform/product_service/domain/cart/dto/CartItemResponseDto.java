package com.smplatform.product_service.domain.cart.dto;

import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import lombok.*;

import java.time.LocalDateTime;
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
        @Setter
        private Integer discountedPrice;
        @Setter
        private List<ProductResponseDto.ProductOptionGet> productOptions;
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

        private Discount.Type discountType;
        private Integer discountValue;
        private LocalDateTime discountStartDate;
        private LocalDateTime discountEndDate;
    }
}
