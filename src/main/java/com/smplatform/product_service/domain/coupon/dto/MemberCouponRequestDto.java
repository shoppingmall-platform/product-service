package com.smplatform.product_service.domain.coupon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberCouponRequestDto {
    @Getter
    public static class CouponIssue {
        @NotBlank
        private String couponIssueCode;

    }
}
