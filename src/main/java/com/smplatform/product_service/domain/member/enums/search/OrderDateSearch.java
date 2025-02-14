package com.smplatform.product_service.domain.member.enums.search;

import lombok.Getter;

@Getter
public enum OrderDateSearch {
    ORDER_DATE("주문일"),
    PAYMENT_DATE("결제일"),
    DELIVERY_DATE("배송일");

    private final String value;

    OrderDateSearch(String value) {
        this.value = value;
    }
}
