package com.smplatform.product_service.domain.coupon.dto;

import com.smplatform.product_service.domain.coupon.entity.CouponApplyType;
import com.smplatform.product_service.domain.coupon.entity.CouponType;
import com.smplatform.product_service.domain.coupon.entity.IssueTargetType;
import com.smplatform.product_service.domain.coupon.entity.IssueType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class CouponRequestDto {

    @Getter
    public static class CouponCreate {
        @NotBlank
        private String couponName;
        @NotNull
        private CouponType couponType;
        @NotNull
        private Integer amount;
        private Integer minOrderPrice;
        private Integer maxDiscountPrice;
        private LocalDate couponStartDate;
        private LocalDate couponEndDate;
        @NotNull
        private IssueType issueType;
        private String couponIssueCode;
        private IssueTargetType issueTargetType;
        private List<String> issueTargetMemberIds;
        private CouponApplyType applyType;
        private List<Long> applyTargetIds;
        private String comment;
    }

    @Getter
    public static class CouponSearch {
        private String couponName;
        private LocalDate couponStartDate;
        private LocalDate couponEndDate;
    }

    @Getter
    public static class CouponDelete {
        @NotNull
        private Long couponId;
    }
}
