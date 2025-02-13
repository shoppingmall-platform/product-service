package com.smplatform.product_service.domain.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String e) {
        super(e);
    }
}
