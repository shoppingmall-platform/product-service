package com.smplatform.product_service.domain.coupon.exception;

import org.springframework.http.HttpStatus;

public class CouponNotFoundException extends AbstractCouponException{
    public CouponNotFoundException(String couponCode) {
        super("Coupon Not Found : " + couponCode);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
