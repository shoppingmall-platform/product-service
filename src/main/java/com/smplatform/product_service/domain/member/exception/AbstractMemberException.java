package com.smplatform.product_service.domain.member.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractMemberException extends RuntimeException {
    protected AbstractMemberException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
