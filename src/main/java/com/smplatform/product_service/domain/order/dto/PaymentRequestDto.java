package com.smplatform.product_service.domain.order.dto;

import lombok.Setter;

public class PaymentRequestDto {
    @Setter
    public static class PaymentConfirm {
        private String paymentKey;
        private String orderId;
        private Long amount;

    }
}
