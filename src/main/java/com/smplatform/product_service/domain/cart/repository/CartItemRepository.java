package com.smplatform.product_service.domain.cart.repository;

import com.smplatform.product_service.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CustomCartItemRepository {
}
