package com.smplatform.product_service.domain.coupon.service.impl;

import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.repository.CouponRepository;
import com.smplatform.product_service.domain.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public String createCoupon(CouponRequestDto.CouponCreate couponRequestDto) {
        Coupon coupon = Coupon.createCoupon(couponRequestDto);
        couponRepository.save(coupon);
        return String.valueOf(coupon.getCouponId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto.CouponInfo> getCouponList(CouponRequestDto.CouponSearch couponSearchDto) {

        return couponRepository.searchCoupon(couponSearchDto).stream()
                .map(CouponResponseDto.CouponInfo::of).collect(Collectors.toList());
    }

    @Override
    public void deleteCoupon(CouponRequestDto.CouponDelete couponRequestDto) {
        couponRepository.deleteById(couponRequestDto.getCouponId());
    }
}
