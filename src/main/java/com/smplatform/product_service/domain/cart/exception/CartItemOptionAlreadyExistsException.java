package com.smplatform.product_service.domain.cart.exception;

import com.smplatform.product_service.exception.AbstractApiException;
import org.springframework.http.HttpStatus;

public class CartItemOptionAlreadyExistsException extends AbstractApiException {
    public CartItemOptionAlreadyExistsException(Long productOptionId) {
        super("이미 장바구니에 존재하는 옵션 ID 입니다 : " + productOptionId);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
