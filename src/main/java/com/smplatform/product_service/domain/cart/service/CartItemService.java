package com.smplatform.product_service.domain.cart.service;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;

import java.util.List;

public interface CartItemService {
    String addCartItems(String memberId, CartItemRequestDto.CartAdd requestDto);

    CartItemResponseDto.CartGet getCartItems(String memberId);

    String updateCartItems(String memberId, List<CartItemRequestDto.CartUpdate> requestDto);

    Void deleteCartItems(String memberId, List<CartItemRequestDto.CartDelete> requestDto);
}
