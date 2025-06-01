package com.smplatform.product_service.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String e) {
        super(e);
    }
}
