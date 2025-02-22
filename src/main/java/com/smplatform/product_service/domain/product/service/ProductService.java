package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto.GetProduct getProduct(int productId);

    String saveProduct(ProductRequestDto.SaveProduct productDto);


    String updateProduct(ProductRequestDto.UpdateProduct productDto);
}
