package com.smplatform.product_service.domain.coupon.dto;

import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyPolicy;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyTarget;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyType;
import com.smplatform.product_service.domain.coupon.entity.CouponIssuePolicy;
import com.smplatform.product_service.domain.coupon.entity.CouponIssueTargetMember;
import com.smplatform.product_service.domain.coupon.entity.CouponType;
import com.smplatform.product_service.domain.coupon.entity.IssueTargetType;
import com.smplatform.product_service.domain.coupon.entity.IssueType;
import lombok.Getter;

import java.util.List;

public class CouponResponseDto {

    @Getter
    public static class CouponInfo {
        private Long couponId;
        private String couponIssueCode;
        private IssueType issueType;
        private IssueTargetType issueTargetType;
        private String couponName;
        private CouponType couponType;
        private int discountAmount;
        private int minOrderPrice;
        private int maxDiscountPrice;
        private String couponStartDate;
        private String couponEndDate;
        private String comment;
        private CouponApplyType applyType;
        private List<String> issueTargetMemberIds;
        private List<Long> applyTargetIds;

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
            CouponIssuePolicy issuePolicy = coupon.getIssuePolicy();
            if (issuePolicy != null) {
                this.issueTargetType = issuePolicy.getTargetType();
                this.issueTargetMemberIds = issuePolicy.getTargets().stream()
                        .map(CouponIssueTargetMember::getMemberId)
                        .toList();
            }
            CouponApplyPolicy applyPolicy = coupon.getApplyPolicy();
            if (applyPolicy != null) {
                this.applyType = applyPolicy.getApplyType();
                this.applyTargetIds = applyPolicy.getTargets().stream()
                        .map(CouponApplyTarget::getTargetId)
                        .toList();
            }
        }

        public static CouponInfo of(Coupon coupon) {
            return new CouponInfo(coupon);
        }

    }
}
