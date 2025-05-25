package com.smplatform.product_service.domain.member.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String e) {
        super(e);
    }
}
