package com.smplatform.product_service.domain.coupon.service.impl;

import com.smplatform.product_service.domain.coupon.dto.MemberCouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;
import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.entity.MemberCoupon;
import com.smplatform.product_service.domain.coupon.exception.CouponNotFoundException;
import com.smplatform.product_service.domain.coupon.repository.CouponRepository;
import com.smplatform.product_service.domain.coupon.repository.MemberCouponRepository;
import com.smplatform.product_service.domain.coupon.service.MemberCouponService;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.exception.MemberNotFoundException;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCouponServiceImpl implements MemberCouponService {
    private final MemberRepository memberRepository ;
    private final CouponRepository couponRepository ;
    private final MemberCouponRepository memberCouponRepository ;

    @Override
    public MemberCouponResponseDto.CouponIssue issueCoupon(String memberId, MemberCouponRequestDto.CouponIssue couponIssueDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new MemberNotFoundException(memberId));
        Coupon coupon = couponRepository.findByCouponCode(couponIssueDto.getCouponIssueCode()).orElseThrow(()-> new CouponNotFoundException(couponIssueDto.getCouponIssueCode()));

        MemberCoupon memberCoupon = MemberCoupon.createMemberCoupon(member, coupon);
        memberCouponRepository.save(memberCoupon);

        return MemberCouponResponseDto.CouponIssue.of(memberCoupon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberCouponResponseDto.MemberCouponInfo> getCoupons(String memberId) {
        memberRepository.findById(memberId).orElseThrow(()->new MemberNotFoundException(memberId));
        return memberCouponRepository.findAllByMemberId(memberId);
    }
}
