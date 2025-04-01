package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = {"discount"})
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"discount", "category", "productTags"})
    Page<Product> findAllByCategoryCategoryId(int categoryId, Pageable pageable);
}
