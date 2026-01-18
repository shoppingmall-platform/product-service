package com.smplatform.product_service.domain.coupon.repository;

import com.smplatform.product_service.domain.coupon.entity.CouponApplyTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponApplyTargetRepository extends JpaRepository<CouponApplyTarget, Long> {
}
