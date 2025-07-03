package com.smplatform.product_service.domain.order.repository;

import com.smplatform.product_service.domain.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
