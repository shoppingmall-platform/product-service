package com.smplatform.product_service.domain.order.repository;

import com.smplatform.product_service.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository, CustomOrderSearchRepository {
}
