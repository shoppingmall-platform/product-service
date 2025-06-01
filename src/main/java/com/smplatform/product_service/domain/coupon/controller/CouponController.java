package com.smplatform.product_service.domain.coupon.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import com.smplatform.product_service.domain.coupon.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coupons")
@Tag(name = "Coupon", description = "Coupon management APIs")
public class CouponController {
    private final CouponService couponService;

    @Operation(summary = "coupon 생성", description = "쇼핑몰 관리자가 쿠폰 생성하는 기능")
    @AdminOnly
    @PostMapping
    public ResponseEntity<String> createCoupon(@RequestBody @Valid CouponRequestDto.CouponCreate couponCreateDto) {
        return ResponseEntity.status(201).body(couponService.createCoupon(couponCreateDto));
    }

    @Operation(summary = "coupon 조회", description = "쇼핑몰에 생성된 쿠폰 목록 조회")
    @AdminOnly
    @PostMapping("/search")
    public ResponseEntity<List<CouponResponseDto.CouponInfo>> getCouponList(@RequestBody @Valid CouponRequestDto.CouponSearch couponSearchDto) {
        return ResponseEntity.ok(couponService.getCouponList(couponSearchDto));
    }

    @Operation(summary = "coupon 삭제", description = "쇼핑몰에 생성된 쿠폰 목록 조회")
    @AdminOnly
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCoupon(@RequestBody @Valid CouponRequestDto.CouponDelete couponRequestDto) {
        couponService.deleteCoupon(couponRequestDto);
        return ResponseEntity.status(204).build();
    }
}
