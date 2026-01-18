package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.entity.CouponApplyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponApplyPolicyRepository extends JpaRepository<CouponApplyPolicy, Long> {
}
