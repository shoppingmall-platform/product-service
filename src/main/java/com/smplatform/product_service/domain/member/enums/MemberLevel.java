package com.smplatform.product_service.domain.member.enums;

import lombok.Getter;

@Getter
public enum MemberLevel {
    VIP("VIP 회원"),
    GENERAL("일반 회원"),
    NEW("신규 회원");

    private final String value;

    MemberLevel(String value) {
        this.value = value;
    }
}
