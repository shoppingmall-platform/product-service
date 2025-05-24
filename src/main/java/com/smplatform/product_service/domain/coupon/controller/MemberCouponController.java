package com.smplatform.product_service.domain.coupon.controller;

import com.smplatform.product_service.domain.coupon.dto.MemberCouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;
import com.smplatform.product_service.domain.coupon.service.MemberCouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MemberCouponController {
    private final MemberCouponService memberCouponService;

    @PostMapping("/coupons/issue")
    public ResponseEntity<MemberCouponResponseDto.CouponIssue> issueCoupon(@RequestHeader("X-USER-ID") String memberId, @RequestBody @Valid MemberCouponRequestDto.CouponIssue couponIssueDto) {
        return ResponseEntity.status(201).body(memberCouponService.issueCoupon(memberId, couponIssueDto)) ;
    }

    @GetMapping("/members/me/coupons")
    public ResponseEntity<List<MemberCouponResponseDto.MemberCouponInfo>> getCoupons(@RequestHeader("X-USER-ID") String memberId) {
        return ResponseEntity.ok(memberCouponService.getCoupons(memberId)) ;
    }

}
