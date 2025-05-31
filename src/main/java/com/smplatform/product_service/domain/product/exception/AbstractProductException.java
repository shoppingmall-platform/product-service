package com.smplatform.product_service.domain.product.exception;

import com.smplatform.product_service.exception.AbstractApiException;

public abstract class AbstractProductException extends AbstractApiException {
    protected AbstractProductException(String message) {
        super(message);
    }
}
