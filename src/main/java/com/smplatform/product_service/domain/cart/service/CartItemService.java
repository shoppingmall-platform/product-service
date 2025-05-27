package com.smplatform.product_service.domain.cart.service;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;

public interface CartItemService {
    String addCartItems(String memberId, CartItemRequestDto.CartAdd requestDto);

    CartItemResponseDto.CartGet getCartItems(String memberId);
}
