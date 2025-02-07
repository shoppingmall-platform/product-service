package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberCredentialDto {
    private String loginId;
    private String password;

    public MemberCredentialDto(Member member) {
        this.loginId = member.getId();
        this.password = member.getPassword();
    }

}
