package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.entity.CouponIssuePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssuePolicyRepository extends JpaRepository<CouponIssuePolicy, Long> {
}
