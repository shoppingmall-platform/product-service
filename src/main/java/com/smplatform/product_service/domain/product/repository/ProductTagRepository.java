package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
}
