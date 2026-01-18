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
@Table(name = "coupon_issue_policies")
public class CouponIssuePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_issue_policy_id")
    private long couponIssuePolicyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(name = "issue_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Column(name = "target_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueTargetType targetType;

    @OneToMany(mappedBy = "issuePolicy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponIssueTargetMember> targets = new ArrayList<>();

    private CouponIssuePolicy(Coupon coupon, IssueType issueType, IssueTargetType targetType) {
        this.coupon = coupon;
        this.issueType = issueType;
        this.targetType = targetType;
    }

    public static CouponIssuePolicy create(Coupon coupon, IssueType issueType, IssueTargetType targetType) {
        return new CouponIssuePolicy(coupon, issueType, targetType);
    }
}
