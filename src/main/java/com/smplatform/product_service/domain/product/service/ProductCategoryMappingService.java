package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductCategoryMappingService {
    public void save(int categoryId, long productId);

    public List<ProductResponseDto.ProductGet> getProductsByCategoryId(int categoryId);

    public void deleteByCategoryId(int categoryId);

    public void deleteByProductId(long productId);

}
