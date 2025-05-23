package com.smplatform.product_service.domain.coupon.exception;

import org.springframework.http.HttpStatus;

public class InvalidEnumValueException extends AbstractCouponException {

    public InvalidEnumValueException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
