package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImages, Long> {
    List<ProductImages> findAllByProductProductId(long productId);
}
