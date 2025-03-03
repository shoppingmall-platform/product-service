package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.ProductOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionDetailRepository extends JpaRepository<ProductOptionDetail, Long> {
    List<ProductOptionDetail> findAllByProductOption_ProductOptionId(long productOptionId);
}
