package com.smplatform.product_service.domain.member.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends AbstractMemberException {
    public InvalidPasswordException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
