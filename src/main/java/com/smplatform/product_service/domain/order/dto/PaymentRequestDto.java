package com.smplatform.product_service.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PaymentRequestDto {
    @Setter
    @Getter
    @AllArgsConstructor
    public static class PaymentConfirm {
        private String paymentKey;
        private String orderId;
        private Long amount;

    }
}
