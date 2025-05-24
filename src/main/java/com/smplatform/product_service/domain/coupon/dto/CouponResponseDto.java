package com.smplatform.product_service.domain.coupon.dto;

import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.entity.CouponType;
import com.smplatform.product_service.domain.coupon.entity.IssueType;
import lombok.Getter;

public class CouponResponseDto {

    @Getter
    public static class CouponInfo {
        private Long couponId;
        private String couponIssueCode;
        private IssueType issueType;
        private String couponName;
        private CouponType couponType;
        private int discountAmount;
        private int minOrderPrice;
        private int maxDiscountPrice;
        private String couponStartDate;
        private String couponEndDate;
        private String comment;

        private CouponInfo(Coupon coupon) {
            this.couponId = coupon.getCouponId();
            this.couponIssueCode = coupon.getCouponCode();
            this.issueType = coupon.getIssueType();
            this.couponName = coupon.getCouponName();
            this.couponType = coupon.getCouponType();
            this.discountAmount = coupon.getDiscountAmount();
            this.minOrderPrice = coupon.getMinOrderPrice();
            this.maxDiscountPrice = coupon.getMaxDiscountPrice();
            this.couponStartDate = coupon.getCouponStartAt() == null ? null : coupon.getCouponStartAt().toString();
            this.couponEndDate = coupon.getCouponEndAt() == null ? null : coupon.getCouponEndAt().toString();
            this.comment = coupon.getComment();
        }

        public static CouponInfo of(Coupon coupon) {
            return new CouponInfo(coupon);
        }

    }
}
