package com.smplatform.product_service.domain.option.repository;

import com.smplatform.product_service.domain.option.entity.OptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OptionTypeRepository extends JpaRepository<OptionType, Long> {
    @EntityGraph(attributePaths = {"optionValues"})
    Page<OptionType> findAll(Pageable pageable);
}
