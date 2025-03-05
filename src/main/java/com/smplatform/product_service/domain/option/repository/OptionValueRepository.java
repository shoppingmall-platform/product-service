package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionValueRepository extends JpaRepository<OptionValue, Integer> {
}
