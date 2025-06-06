package com.smplatform.product_service.domain.order.controller;

import com.smplatform.product_service.domain.order.dto.OrderRequestDto;
import com.smplatform.product_service.domain.order.service.OrderService;
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
    public ResponseEntity<Long> saveOrder(@RequestBody OrderRequestDto.OrderSave requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(requestDto));
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
