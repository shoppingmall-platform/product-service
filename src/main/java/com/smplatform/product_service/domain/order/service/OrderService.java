package com.smplatform.product_service.domain.order.service;

import com.smplatform.product_service.domain.order.dto.OrderRequestDto;

public interface OrderService {
    Long saveOrder(OrderRequestDto.OrderSave requestDto);
}
