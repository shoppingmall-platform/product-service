package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.entity.Coupon;

import java.util.List;

public interface CustomCouponRepository {
    public List<Coupon> searchCoupon(CouponRequestDto.CouponSearch couponSearchDto);
}
