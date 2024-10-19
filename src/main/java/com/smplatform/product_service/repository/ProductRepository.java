package com.smplatform.product_service.repository;

import com.smplatform.product_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
