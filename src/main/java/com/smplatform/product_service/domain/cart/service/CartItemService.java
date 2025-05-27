package com.smplatform.product_service.domain.cart.service;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;

import java.util.List;

public interface CartItemService {
    String addCartItems(String memberId, List<CartItemRequestDto.CartAdd> requestDto);

    String updateCartItems(String memberId, List<CartItemRequestDto.CartUpdate> requestDto);
}
