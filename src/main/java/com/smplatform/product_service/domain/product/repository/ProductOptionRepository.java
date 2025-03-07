package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findAllByProduct_Id(long productId);
}
