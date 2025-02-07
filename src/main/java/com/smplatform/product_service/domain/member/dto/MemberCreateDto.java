package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.enums.Gender;
import com.smplatform.product_service.domain.member.enums.MemberAuthority;
import com.smplatform.product_service.domain.member.enums.MemberLevel;
import com.smplatform.product_service.domain.member.enums.MemberStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class MemberCreateDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private LocalDate birthday;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Gender gender;

    private String region;  // Optional

    private Boolean tosAgreement; // Optional

    private Boolean privacyAgreement; // Optional

    private Boolean marketingAgreement;  // Optional

    public Member toEntity(String hashedPassword) {
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(hashedPassword) // Set Password to hashedPassword from arg
                .birthday(birthday) // Set as null if not provided
                .phoneNumber(phoneNumber)
                .gender(gender)
                .status(MemberStatus.ACTIVE) // Set default status
                .authority(MemberAuthority.USER) // Set default authority
                .level(MemberLevel.NEW) // Set default level
                .region(region != null ? region : "") // Default to empty string if null
                .tosAgreement(tosAgreement != null ? tosAgreement : false) // Default to false if null
                .privacyAgreement(privacyAgreement != null ? privacyAgreement : false) // Default to false if null
                .marketingAgreement(marketingAgreement != null ? marketingAgreement : false) // Default to false if null
                .build();
    }
}