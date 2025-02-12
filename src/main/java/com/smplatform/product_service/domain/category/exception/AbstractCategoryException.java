package com.smplatform.product_service.domain.category.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractCategoryException extends RuntimeException {
    public AbstractCategoryException() {}

    public AbstractCategoryException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
