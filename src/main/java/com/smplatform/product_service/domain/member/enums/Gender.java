package com.smplatform.product_service.domain.member.enums;

import lombok.Getter;

@Getter
public enum Gender {
    M("남성"),
    F("여성");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}