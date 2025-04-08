package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.ProductCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryMappingRepository extends JpaRepository<ProductCategoryMapping, Integer> {
}
