package com.smplatform.product_service.domain.cart.service;

import com.smplatform.product_service.domain.cart.dto.CartRequestDto;

public interface CartService {
    String addCartItems(String memberId, CartRequestDto.CartAdd requestDto);
}
