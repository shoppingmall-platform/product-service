package com.smplatform.product_service.domain.member.enums;

import lombok.Getter;

@Getter
public enum MemberStatus {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    DORMANT("휴면"),
    WITHDRAWN("탈퇴"),
    SUSPENDED("정지"),
    PENDING_EMAIL_VERIFICATION("이메일 인증 대기"),
    PENDING_ADMIN_APPROVAL("관리자 승인 대기");

    private final String value;

    MemberStatus(String value) {
        this.value = value;
    }
}
