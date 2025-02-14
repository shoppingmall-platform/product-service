package com.smplatform.product_service.domain.member.enums;

import lombok.Getter;

@Getter
public enum MemberAuthority {
    USER("일반"),
    ADMIN("관리자");

    private final String value;

    MemberAuthority(String value) {
        this.value = value;
    }
}
