package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.OptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionTypeRepository extends JpaRepository<OptionType, Integer> {
}
