package com.smplatform.product_service.domain.product.dto;

import com.smplatform.product_service.domain.product.entity.ProductImages;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductImageResponseDto {
    private ProductImageResponseDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    public static class ProductImageInfo {
        private long productImageId;
        private String path;

        public static ProductImageInfo from(ProductImages productImages) {
            return new ProductImageInfo(
                    productImages.getProductImageId(),
                    productImages.getPath()
            );
        }
    }
}
