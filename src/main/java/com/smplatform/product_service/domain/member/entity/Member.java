package com.smplatform.product_service.domain.member.entity;

import com.smplatform.product_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.product_service.domain.member.enums.Gender;
import com.smplatform.product_service.domain.member.enums.MemberAuthority;
import com.smplatform.product_service.domain.member.enums.MemberLevel;
import com.smplatform.product_service.domain.member.enums.MemberStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@Builder
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @NotBlank
    @Column(name = "id", nullable = false)
    private String id;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @NotBlank
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private MemberAuthority authority;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private MemberLevel level;

    @Column(name = "region")
    private String region;

    @Column(name = "tos_agreement")
    private Boolean tosAgreement;

    @Column(name = "privacy_agreement")
    private Boolean privacyAgreement;

    @Column(name = "marketing_agreement")
    private Boolean marketingAgreement;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;


    public void update(MemberUpdateDto memberUpdateDto) {
        Optional.ofNullable(memberUpdateDto.getName()).ifPresent(name -> this.name = name);
        Optional.ofNullable(memberUpdateDto.getBirthday()).ifPresent(birthday -> this.birthday = birthday);
        Optional.ofNullable(memberUpdateDto.getPhoneNumber()).ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);
        Optional.ofNullable(memberUpdateDto.getGender()).ifPresent(gender -> this.gender = gender);
        Optional.ofNullable(memberUpdateDto.getStatus()).ifPresent(status -> this.status = status);
        Optional.ofNullable(memberUpdateDto.getLevel()).ifPresent(level -> this.level = level);
        Optional.ofNullable(memberUpdateDto.getRegion()).ifPresent(region -> this.region = region);
        Optional.ofNullable(memberUpdateDto.getTosAgreement()).ifPresent(tosAgreement -> this.tosAgreement = tosAgreement);
        Optional.ofNullable(memberUpdateDto.getPrivacyAgreement()).ifPresent(privacyAgreement -> this.privacyAgreement = privacyAgreement);
        Optional.ofNullable(memberUpdateDto.getMarketingAgreement()).ifPresent(marketingAgreement -> this.marketingAgreement = marketingAgreement);
    }

    public void delete() {
        this.status = MemberStatus.WITHDRAWN;
    }

}
