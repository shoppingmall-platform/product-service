package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findAllByProduct_Id(int productId);
}
