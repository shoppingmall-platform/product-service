package com.smplatform.product_service.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractApiException extends RuntimeException {
    public AbstractApiException(String message) {
        super(message);
    }

    protected AbstractApiException() {
    }

    public abstract HttpStatus getHttpStatus();
}
