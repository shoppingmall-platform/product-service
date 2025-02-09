package com.smplatform.product_service.domain.discount.repository;

import com.smplatform.product_service.domain.discount.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
//    @Query("SELECT d FROM Discount d WHERE d.discountStartDate >= :startDate AND d.discountEndDate <= :endDate")
    Optional<ArrayList<Discount>> findByStartDateBetweenAndEndDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

//    @Query("SELECT d FROM Discount d WHERE d.discount_start_date >= :startDate AND d.discount_end_date <= :endDate AND d.discount_name = :discountName")
    Optional<ArrayList<Discount>> findByStartDateBetweenAndEndDateBetweenAndDiscountName(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("discountName") String discountName);

}
