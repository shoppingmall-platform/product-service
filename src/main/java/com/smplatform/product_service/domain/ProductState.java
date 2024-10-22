package com.smplatform.product_service.domain;

import com.smplatform.product_service.exception.ProductStateNotFoundException;

public enum ProductState {
    NEW(1),
    USED(2),
    REFURBISHED(3);

    private int code;

    ProductState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public ProductState getProductStateFromCode(int code) throws ProductStateNotFoundException {
        if (code == 1) {
            return NEW;
        } else if (code == 2) {
            return USED;
        } else if (code == 3) {
            return REFURBISHED;
        }
        throw new ProductStateNotFoundException();
    }
}
