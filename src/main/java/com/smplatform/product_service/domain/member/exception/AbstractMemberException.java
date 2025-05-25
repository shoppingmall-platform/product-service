package com.smplatform.product_service.domain.member.exception;

import com.smplatform.product_service.exception.AbstractApiException;

public abstract class AbstractMemberException extends AbstractApiException {
    protected AbstractMemberException(String message) {
        super(message);
    }
}
