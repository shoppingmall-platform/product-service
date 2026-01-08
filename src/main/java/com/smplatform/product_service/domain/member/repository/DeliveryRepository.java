package com.smplatform.product_service.domain.member.repository;

import com.smplatform.product_service.domain.member.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
