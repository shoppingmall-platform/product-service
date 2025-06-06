package com.smplatform.product_service.domain.order.entity;

public enum OrderStatus {
    DEPENDING(1),
    PROGRESSING(2),
    COMPLETE(3);

    private final int code;

    OrderStatus(int i) {
        this.code = i;
    }

}
