package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.Product;

import java.util.List;

public interface CustomProductCategoryRepository {
    List<Product> findAllByCategoryId(int categoryId);
}
