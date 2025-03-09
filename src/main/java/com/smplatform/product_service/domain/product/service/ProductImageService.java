package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductImageResponseDto;

import java.util.List;

public interface ProductImageService {
    List<ProductImageResponseDto.ProductImageInfo> getProductProductImageList(Long productId);

    void saveProductImages(long productId, List<String> paths);

    void deleteProductImage(List<Long> ProductImageIds);
}
