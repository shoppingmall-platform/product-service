package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductRequestDto;

public interface ProductService {
    ProductRequestDto getProduct(int productId);

    String saveProduct(ProductRequestDto productDto);


    String updateProduct(ProductRequestDto productRequestDto);
}
