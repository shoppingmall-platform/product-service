package com.smplatform.product_service.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractApiException extends RuntimeException {
    public AbstractApiException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
