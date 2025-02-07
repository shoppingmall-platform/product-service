package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {
    private final String id;
    private final String name;
    private final String email;
    private final LocalDate birthday;
    private final String phoneNumber;
    private final String gender;
    private final String status;
    private final String level;
    private final String region;
    private final LocalDateTime createAt;
    private final String authority;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.phoneNumber = member.getPhoneNumber();
        this.gender = member.getGender().toString();
        this.status = member.getStatus().toString();
        this.level = member.getLevel().toString();
        this.region = member.getRegion();
        this.createAt = member.getCreateAt();
        this.authority = member.getAuthority().toString();
    }
}
