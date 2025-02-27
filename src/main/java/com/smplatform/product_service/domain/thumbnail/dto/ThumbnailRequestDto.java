package com.smplatform.product_service.domain.thumbnail.dto;

import com.smplatform.product_service.domain.product.domain.Product;
import com.smplatform.product_service.domain.thumbnail.entity.Thumbnail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ThumbnailRequestDto {
    private ThumbnailRequestDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class SaveThumbnail {
        private int productId;
        private List<String> paths;

        public Thumbnail toEntity(Product product, String path) {
            return Thumbnail.builder()
                    .product(product)
                    .path(path)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class DeleteThumbnail {
        private long thumbnailId;
    }
}
