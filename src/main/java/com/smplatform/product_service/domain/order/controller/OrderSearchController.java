package com.smplatform.product_service.domain.order.controller;

import com.smplatform.product_service.domain.order.dto.OrderSearchRequestDto;
import com.smplatform.product_service.domain.order.dto.OrderSearchResponseDto;
import com.smplatform.product_service.domain.order.service.OrderSearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderSearchController {
    private final OrderSearchService orderSearchService;

    @Operation(summary = "member order list", description = "사용자의 주문내역 조회")
    @PostMapping("/search")
    public Page<OrderSearchResponseDto.MemberOrder> searchMemberOrders(
            @RequestHeader("X-MEMBER-ID") String memberId,
            @RequestBody OrderSearchRequestDto.MemberOrdersSearch request) {
        return orderSearchService.getMemberOrders(memberId, request);
    }
}
