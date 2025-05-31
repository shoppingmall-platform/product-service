package com.smplatform.product_service.domain.coupon.controller;

import com.smplatform.product_service.domain.coupon.dto.MemberCouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;
import com.smplatform.product_service.domain.coupon.service.MemberCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "MemberCoupon", description = "MemberCoupon management APIs")
public class MemberCouponController {
    private final MemberCouponService memberCouponService;

    @Operation(summary = "회원 쿠폰 발급", description = "해당 아이디에 쿠폰 발급")
    @PostMapping("/coupons/issue")
    public ResponseEntity<MemberCouponResponseDto.CouponIssue> issueCoupon(@RequestHeader("X-MEMBER-ID") String memberId, @RequestBody @Valid MemberCouponRequestDto.CouponIssue couponIssueDto) {
        return ResponseEntity.status(201).body(memberCouponService.issueCoupon(memberId, couponIssueDto)) ;
    }

    @Operation(summary = "회원 쿠폰 조회", description = "해당 아이디에 발급된 쿠폰 조회")
    @GetMapping("/members/me/coupons")
    public ResponseEntity<List<MemberCouponResponseDto.MemberCouponInfo>> getCoupons(@RequestHeader("X-MEMBER-ID") String memberId) {
        return ResponseEntity.ok(memberCouponService.getCoupons(memberId)) ;
    }

}
