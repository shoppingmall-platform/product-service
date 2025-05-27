package com.smplatform.product_service.domain.cart.exception;

import com.smplatform.product_service.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends AbstractApiException {
    public CartItemNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
