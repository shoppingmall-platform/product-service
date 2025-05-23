package com.smplatform.product_service.domain.coupon.controller;

import com.smplatform.product_service.domain.coupon.service.MemberCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MemberCouponController {
    private final MemberCouponService memberCouponService;

    @PostMapping("/coupons/issue")
    public ResponseEntity<String> issueCoupon() {
        return null;
    }

    @GetMapping("/members/me/coupons")
    public ResponseEntity<String> useCoupon() {
        return null;
    }

}
