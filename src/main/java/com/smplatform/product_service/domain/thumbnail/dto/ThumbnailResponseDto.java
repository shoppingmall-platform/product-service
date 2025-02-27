package com.smplatform.product_service.domain.thumbnail.dto;

import com.smplatform.product_service.domain.thumbnail.entity.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ThumbnailResponseDto {
    private ThumbnailResponseDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    public static class ThumbnailInfo {
        private long thumbnailId;
        private String path;

        public static ThumbnailInfo from(Thumbnail thumbnail) {
            return new ThumbnailInfo(
                    thumbnail.getThumbnailId(),
                    thumbnail.getPath()
            );
        }
    }
}
