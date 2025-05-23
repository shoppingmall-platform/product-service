package com.smplatform.product_service.domain.coupon.dto;

import com.smplatform.product_service.domain.coupon.entity.CouponType;
import com.smplatform.product_service.domain.coupon.entity.IssueType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

public class CouponRequestDto {

    @Getter
    public static class CouponCreate {
        @NotBlank
        private String couponName;
        @NotBlank
        private CouponType couponType;
        @NotBlank
        private Integer amount;
        private Integer minOrderPrice;
        private Integer maxDiscountPrice;
        private LocalDateTime couponStartDate;
        private LocalDateTime couponEndDate;
        @NotBlank
        private IssueType issueType;
        private String couponIssueCode;
        private String comment;
    }

    @Getter
    public static class CouponSearch {
    }

    @Getter
    public static class CouponDelete {
        @NotBlank
        private Long couponId;
    }
}
