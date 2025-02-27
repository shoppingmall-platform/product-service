package com.smplatform.product_service.domain.thumbnail.exception;

public class ThumbnailNotFoundException extends RuntimeException {
    public ThumbnailNotFoundException(String msg) {
        super(msg);
    }
}
