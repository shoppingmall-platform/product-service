package com.smplatform.product_service.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_apply_targets")
public class CouponApplyTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_apply_target_id")
    private long couponApplyTargetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_apply_policy_id", nullable = false)
    private CouponApplyPolicy applyPolicy;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    private CouponApplyTarget(CouponApplyPolicy applyPolicy, Long targetId) {
        this.applyPolicy = applyPolicy;
        this.targetId = targetId;
    }

    public static CouponApplyTarget create(CouponApplyPolicy applyPolicy, Long targetId) {
        return new CouponApplyTarget(applyPolicy, targetId);
    }
}
