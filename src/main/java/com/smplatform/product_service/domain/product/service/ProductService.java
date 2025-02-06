package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductDto;

public interface ProductService {
    ProductDto getProduct(int productId);

    ProductDto saveProduct(ProductDto productDto);
}
