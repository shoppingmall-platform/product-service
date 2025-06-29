package com.smplatform.product_service.domain.order.repository;

import com.smplatform.product_service.domain.order.dto.OrderResponseDto;

import java.util.List;

public interface CustomOrderRepository {

    List<OrderResponseDto.ProductOptionFlatDto> findAllByProductOptionIds(List<Long> productOptionIds);
}
