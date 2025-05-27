package com.smplatform.product_service.domain.cart.repository;

import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;

import java.util.List;

public interface CustomCartItemRepository {

    List<CartItemResponseDto.CartItemFlatDto> findAllByMemberId(String memberId);
}
