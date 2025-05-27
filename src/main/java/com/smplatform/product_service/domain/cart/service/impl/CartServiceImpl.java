package com.smplatform.product_service.domain.cart.service.impl;

import com.smplatform.product_service.domain.cart.dto.CartRequestDto;
import com.smplatform.product_service.domain.cart.repository.CartRepository;
import com.smplatform.product_service.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public String addCartItems(String memberId, CartRequestDto.CartAdd requestDto) {
        return "";
    }
}
