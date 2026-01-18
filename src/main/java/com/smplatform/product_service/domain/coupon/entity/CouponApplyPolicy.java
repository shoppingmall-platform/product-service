package com.smplatform.product_service.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_apply_policies")
public class CouponApplyPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_apply_policy_id")
    private long couponApplyPolicyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(name = "apply_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponApplyType applyType;

    @OneToMany(mappedBy = "applyPolicy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponApplyTarget> targets = new ArrayList<>();

    private CouponApplyPolicy(Coupon coupon, CouponApplyType applyType) {
        this.coupon = coupon;
        this.applyType = applyType;
    }

    public static CouponApplyPolicy create(Coupon coupon, CouponApplyType applyType) {
        return new CouponApplyPolicy(coupon, applyType);
    }
}
