package com.smplatform.product_service.domain.coupon.exception;

import com.smplatform.product_service.exception.AbstractApiException;

public abstract class AbstractCouponException extends AbstractApiException {
    protected AbstractCouponException(String message) {
        super(message);
    }
}
