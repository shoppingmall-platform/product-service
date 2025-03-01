package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}
