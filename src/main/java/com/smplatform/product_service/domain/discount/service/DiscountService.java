package com.smplatform.product_service.domain.discount.service;

import com.smplatform.product_service.domain.discount.dto.DiscountRequestDto;
import com.smplatform.product_service.domain.discount.dto.DiscountResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface DiscountService {

    // 할인코드 목록 조회
    ArrayList<DiscountResponseDto.DiscountInfo> getDiscountList(LocalDateTime startDate, LocalDateTime endDate, String discountName);

    // 할인코드 등록
    String createDiscount(DiscountRequestDto.RegisterDiscount discountRequestDto);
}
