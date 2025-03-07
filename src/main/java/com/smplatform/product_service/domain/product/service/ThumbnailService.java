package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ThumbnailResponseDto;

import java.util.List;

public interface ThumbnailService {
    List<ThumbnailResponseDto.ThumbnailInfo> getProductThumbnailList(Long productId);

    void saveThumbnails(long productId, List<String> paths);

    void deleteThumbnail(List<Long> thumbnailIds);
}
