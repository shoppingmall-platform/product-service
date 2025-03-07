package com.smplatform.product_service.domain.product.exception;

public class ThumbnailNotFoundException extends RuntimeException {
    public ThumbnailNotFoundException(String msg) {
        super(msg);
    }
}
