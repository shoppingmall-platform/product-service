package com.smplatform.product_service.domain.thumbnail.service;

import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailResponseDto;

import java.util.List;

public interface ThumbnailService {
    List<ThumbnailResponseDto.ThumbnailInfo> getProductThumbnailList(Integer productId);

    void saveThumbnail(int productId, List<String> paths);

    void deleteThumbnail(long thumbnailId);
}
