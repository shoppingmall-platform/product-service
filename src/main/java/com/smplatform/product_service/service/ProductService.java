package com.smplatform.product_service.service;

import com.smplatform.product_service.dto.ProductDto;

public interface ProductService {
    ProductDto getProduct(int productId);

    ProductDto saveProduct(ProductDto productDto);
}
