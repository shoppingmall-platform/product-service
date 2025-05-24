package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;

import java.util.List;

public interface CustomMemberCouponRepository {
    List<MemberCouponResponseDto.MemberCouponInfo> findAllByMemberId(String memberId);
}
