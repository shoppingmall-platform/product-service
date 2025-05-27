package com.smplatform.product_service.domain.cart.exception;

import com.smplatform.product_service.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends AbstractApiException {
    public CartItemNotFoundException(Long cartItemId) {
        super("존재하지 않는 장바구니 아이템 ID 입니다 : " + cartItemId);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
