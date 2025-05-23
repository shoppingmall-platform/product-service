package com.smplatform.product_service.domain.coupon.service;

import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;

import java.util.List;

public class CouponServiceImpl implements CouponService {
    @Override
    public String createCoupon(CouponRequestDto.CouponCreate couponRequestDto) {
        return "";
    }

    @Override
    public List<CouponResponseDto.CouponInfo> getCouponList(CouponRequestDto.CouponSearch couponSearchDto) {
        return List.of();
    }

    @Override
    public void deleteCoupon(CouponRequestDto.CouponDelete couponRequestDto) {

    }
}
