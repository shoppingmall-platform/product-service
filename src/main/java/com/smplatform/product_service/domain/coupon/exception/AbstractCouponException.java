package com.smplatform.product_service.domain.coupon.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractCouponException extends RuntimeException {
    protected AbstractCouponException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
