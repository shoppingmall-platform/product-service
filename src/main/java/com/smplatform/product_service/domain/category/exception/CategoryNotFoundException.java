package com.smplatform.product_service.domain.category.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends AbstractCategoryException {
    public CategoryNotFoundException() {}
    public CategoryNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
