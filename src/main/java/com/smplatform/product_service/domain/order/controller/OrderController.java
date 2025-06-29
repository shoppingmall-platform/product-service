package com.smplatform.product_service.domain.order.controller;

import com.smplatform.product_service.domain.order.dto.OrderRequestDto;
import com.smplatform.product_service.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "주문 결제시 호출하는 API", description = "주문 완료시 해당 API를 호출, cartItemId는 Nullable")
    public ResponseEntity<Long> saveOrder(@RequestHeader(name = "X-MEMBER-ID") String id,
                                          @RequestBody OrderRequestDto.OrderSave requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(id, requestDto));
    }

    @GetMapping
    public ResponseEntity<?> getOrder() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateOrder() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> cancelOrder() {
        return null;
    }
}
