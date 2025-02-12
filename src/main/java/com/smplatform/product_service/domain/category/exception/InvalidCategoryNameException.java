package com.smplatform.product_service.domain.category.exception;

import org.springframework.http.HttpStatus;

public class InvalidCategoryNameException extends AbstractCategoryException {
    public InvalidCategoryNameException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
