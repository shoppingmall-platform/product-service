package com.smplatform.product_service.domain.member.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractApiException extends RuntimeException {
    protected AbstractApiException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
