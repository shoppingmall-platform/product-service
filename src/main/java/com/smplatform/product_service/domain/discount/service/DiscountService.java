package com.smplatform.product_service.domain.discount.service;

import com.smplatform.product_service.domain.discount.dto.DiscountDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface DiscountService {

    // 할인코드 목록 조회
    ArrayList<DiscountDto> getDiscountList(LocalDateTime startDate, LocalDateTime endDate, String discountName);

    // 할인코드 등록
    DiscountDto createDiscount(DiscountDto discountDto);
}
