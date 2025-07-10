package com.smplatform.product_service.domain.order.service;

import com.smplatform.product_service.domain.order.dto.OrderSearchRequestDto;
import com.smplatform.product_service.domain.order.dto.OrderSearchResponseDto;
import org.springframework.data.domain.Page;

public interface OrderSearchService {
    Page<OrderSearchResponseDto.MemberOrder> getMemberOrders(String memberId, OrderSearchRequestDto.MemberOrdersSearch request);
}
