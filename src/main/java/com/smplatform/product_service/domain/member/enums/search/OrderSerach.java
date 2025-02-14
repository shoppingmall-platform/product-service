package com.smplatform.product_service.domain.member.enums.search;

import lombok.Getter;

@Getter
public enum OrderSerach {
    TOTAL_ORDER_AMOUNT("총 주문금액"),
    TOTAL_PAYMENT_AMOUNT("총 실결제금액"),
    TOTAL_ORDER_COUNT("총 주문건수"),
    TOTAL_ACTUAL_ORDER_COUNT("총 실주문건수");

    private final String value;

    OrderSerach(String value) {
        this.value = value;
    }
}
