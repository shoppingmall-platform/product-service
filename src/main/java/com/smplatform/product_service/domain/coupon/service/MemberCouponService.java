package com.smplatform.product_service.domain.coupon.service;

import com.smplatform.product_service.domain.coupon.dto.MemberCouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;

import java.util.List;

public interface MemberCouponService {
    MemberCouponResponseDto.CouponIssue issueCoupon(String memberId, MemberCouponRequestDto.CouponIssue couponIssueDto);

    List<MemberCouponResponseDto.MemberCouponInfo> getCoupons(String memberId);
}
