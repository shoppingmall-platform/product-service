package com.smplatform.product_service.domain.discount.repository;

import com.smplatform.product_service.domain.discount.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
//    @Query("SELECT d FROM Discount d WHERE d.discountStartDate >= :startDate AND d.discountEndDate <= :endDate")
    Optional<ArrayList<Discount>> findByDiscountStartDateGreaterThanEqualAndDiscountEndDateLessThanEqual(@Param("startDate") LocalDateTime discountStartDate, @Param("endDate") LocalDateTime discountEndDate);

//    @Query("SELECT d FROM Discount d WHERE d.discount_start_date >= :startDate AND d.discount_end_date <= :endDate AND d.discount_name = :discountName")
    Optional<ArrayList<Discount>> findByDiscountStartDateGreaterThanEqualAndDiscountEndDateLessThanEqualAndDiscountName(
        @Param("startDate") LocalDateTime discountStartDate,
        @Param("endDate") LocalDateTime discountEndDate,
        @Param("discountName") String discountName
    );

    @Query("SELECT d FROM Discount d " +
            "WHERE (:referenceDate IS NULL OR " + // referenceDate가 NULL이면 날짜 조건 무시
            "      (:referenceDate = '시작일' AND " +
            "          (:startDate IS NULL OR d.discountStartDate >= :startDate) AND " +
            "          (:endDate IS NULL OR d.discountStartDate <= :endDate)) " +
            "   OR (:referenceDate = '종료일' AND " +
            "          (:startDate IS NULL OR d.discountEndDate >= :startDate) AND " +
            "          (:endDate IS NULL OR d.discountEndDate <= :endDate))) " +
            "AND (:discountName IS NULL OR d.discountName = :discountName)")
    List<Discount> findDiscounts(
            @Param("referenceDate") String referenceDate,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("discountName") String discountName
    );



}
