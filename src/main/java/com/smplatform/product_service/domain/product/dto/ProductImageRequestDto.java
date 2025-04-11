package com.smplatform.product_service.domain.product.dto;

import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.ProductImages;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductImageRequestDto {
    private ProductImageRequestDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class ProductImageSave {
        private List<String> paths;

        public ProductImages toEntity(Product product, String path) {
            return ProductImages.builder()
                    .product(product)
                    .path(path)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class DeleteProductImage {
        private long productImageId;
    }
}
