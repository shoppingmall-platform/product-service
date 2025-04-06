package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProductRepository {

    Page<Product> searchProducts(int categoryId, ProductRequestDto.ProductSearchCondition condition, Pageable pageable);
}
