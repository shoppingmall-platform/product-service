package com.smplatform.product_service.domain.member.enums.search;

import lombok.Getter;

@Getter
public enum DateSerach {
    JOIN("가입일"),
    BIRTHDAY("생일");

    private final String value;

    DateSerach(String value) {
        this.value = value;
    }
}
