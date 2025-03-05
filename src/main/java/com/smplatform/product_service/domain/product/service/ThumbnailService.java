package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ThumbnailResponseDto;

import java.util.List;

public interface ThumbnailService {
    List<ThumbnailResponseDto.ThumbnailInfo> getProductThumbnailList(Integer productId);

    void saveThumbnail(int productId, List<String> paths);

    void deleteThumbnail(long thumbnailId);
}
