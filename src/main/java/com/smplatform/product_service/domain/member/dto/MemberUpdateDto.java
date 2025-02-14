package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.enums.Gender;
import com.smplatform.product_service.domain.member.enums.MemberLevel;
import com.smplatform.product_service.domain.member.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class MemberUpdateDto {
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private Gender gender;
    private MemberStatus status;
    private MemberLevel level;
    private String region;
    private Boolean tosAgreement;
    private Boolean privacyAgreement;
    private Boolean marketingAgreement;
}
