package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.ProductCategoryMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomProductRepository {

    Page<Product> searchProducts(ProductRequestDto.AdminProductSearchCondition condition, Pageable pageable);

    Page<Product> searchUserProducts(int categoryId, ProductRequestDto.UserProductSearchCondition condition, Pageable pageable);

    Page<Product> findAllByCategoryIds(List<ProductCategoryMapping> productCategoryMappings);
}
