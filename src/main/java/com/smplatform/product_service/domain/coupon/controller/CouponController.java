package com.smplatform.product_service.domain.coupon.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import com.smplatform.product_service.domain.coupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @AdminOnly
    @PostMapping
    public ResponseEntity<String> createCoupon(@RequestBody @Valid CouponRequestDto.CouponCreate couponCreateDto) {
        return ResponseEntity.status(201).body(couponService.createCoupon(couponCreateDto));
    }

    @AdminOnly
    @PostMapping("/search")
    public ResponseEntity<List<CouponResponseDto.CouponInfo>> getCouponList(@RequestBody @Valid CouponRequestDto.CouponSearch couponSearchDto) {
        return ResponseEntity.ok(couponService.getCouponList(couponSearchDto));
    }

    @AdminOnly
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCoupon(@RequestBody @Valid CouponRequestDto.CouponDelete couponRequestDto) {
        couponService.deleteCoupon(couponRequestDto);
        return ResponseEntity.status(204).build();
    }
}
