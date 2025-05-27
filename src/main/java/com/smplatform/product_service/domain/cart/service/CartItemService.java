package com.smplatform.product_service.domain.cart.service;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;

public interface CartItemService {
    String addCartItems(String memberId, CartItemRequestDto.CartAdd requestDto);
}
