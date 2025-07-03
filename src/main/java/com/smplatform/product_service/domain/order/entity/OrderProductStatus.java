package com.smplatform.product_service.domain.order.entity;

public enum OrderProductStatus {
    PAYMENT_PENDING("결제대기"),
    PAYMENT_COMPLETED("결제완료"),
    PREPARING_SHIPMENT("배송준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료"),
    EXCHANGE_REQUESTED("교환신청완료"),
    RECEIVING("수령중"),
    RECEIVED("수령완료"),
    EXCHANGE_CANCELLED("교환취소"),
    EXCHANGE_COMPLETED("교환완료"),
    RETURN_REQUESTED("반품신청완료"),
    RETURN_COMPLETED("반품완료");

    private final String description;

    OrderProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
