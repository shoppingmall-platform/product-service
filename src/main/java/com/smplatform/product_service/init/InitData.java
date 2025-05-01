package com.smplatform.product_service.init;

import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.enums.Gender;
import com.smplatform.product_service.domain.member.enums.MemberAuthority;
import com.smplatform.product_service.domain.member.enums.MemberLevel;
import com.smplatform.product_service.domain.member.enums.MemberStatus;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitData {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initTestData() {
        Member member = new Member(
                "jle",
                passwordEncoder.encode("123!@#"),
                "testadmin",
                "jle@test.com",
                LocalDate.of(2025, 2, 6),
                "010-2244-5555",
                Gender.FEMALE,
                MemberStatus.ACTIVE,
                MemberAuthority.ADMIN,
                MemberLevel.VIP,
                "경남",
                true,
                true,
                true,
                LocalDateTime.now(),
                null);

        memberRepository.save(member);

    }
}
