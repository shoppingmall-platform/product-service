package com.smplatform.product_service.domain.order.repository;

import com.smplatform.product_service.domain.order.entity.OrderBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBenefitRepository extends JpaRepository<OrderBenefit, Long> {
}
