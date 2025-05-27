package com.smplatform.product_service.domain.cart.service.impl;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;
import com.smplatform.product_service.domain.cart.repository.CartItemRepository;
import com.smplatform.product_service.domain.cart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public String addCartItems(String memberId, CartItemRequestDto.CartAdd requestDto) {
        return "";
    }
}
