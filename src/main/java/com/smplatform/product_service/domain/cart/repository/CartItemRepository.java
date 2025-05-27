package com.smplatform.product_service.domain.cart.repository;

import com.smplatform.product_service.domain.cart.entity.CartItem;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, CustomCartItemRepository {
    Optional<CartItem> findByMemberAndProductOption(Member member, ProductOption productOption);

    List<CartItem> findByMemberAndCartItemIdIn(Member member, List<Long> cartItemIds);
}
