package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.entity.CouponIssueTargetMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueTargetMemberRepository extends JpaRepository<CouponIssueTargetMember, Long> {
}
