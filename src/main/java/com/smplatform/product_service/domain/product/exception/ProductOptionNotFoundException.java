package com.smplatform.product_service.domain.product.exception;

import org.springframework.http.HttpStatus;

public class ProductOptionNotFoundException extends AbstractProductException {

    public ProductOptionNotFoundException(Long productOptionId) {
        super("존재하지 않는 상품 옵션 ID 입니다 : " + productOptionId);
    }

    public ProductOptionNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
