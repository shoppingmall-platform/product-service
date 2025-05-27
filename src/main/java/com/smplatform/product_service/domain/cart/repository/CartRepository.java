package com.smplatform.product_service.domain.cart.repository;

import com.smplatform.product_service.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
}
