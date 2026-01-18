package com.smplatform.product_service.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_issue_target_members")
public class CouponIssueTargetMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_issue_target_member_id")
    private long couponIssueTargetMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_issue_policy_id", nullable = false)
    private CouponIssuePolicy issuePolicy;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    private CouponIssueTargetMember(CouponIssuePolicy issuePolicy, String memberId) {
        this.issuePolicy = issuePolicy;
        this.memberId = memberId;
    }

    public static CouponIssueTargetMember create(CouponIssuePolicy issuePolicy, String memberId) {
        return new CouponIssueTargetMember(issuePolicy, memberId);
    }
}
