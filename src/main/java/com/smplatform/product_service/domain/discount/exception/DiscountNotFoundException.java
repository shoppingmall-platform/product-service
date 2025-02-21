package com.smplatform.product_service.domain.discount.exception;

import com.smplatform.product_service.domain.category.exception.AbstractCategoryException;
import org.springframework.http.HttpStatus;

public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException(String message) {
        super(message);
    }
}