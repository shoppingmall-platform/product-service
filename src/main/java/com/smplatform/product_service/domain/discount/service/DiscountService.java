package com.smplatform.product_service.domain.discount.service;

import com.smplatform.product_service.domain.discount.dto.DiscountDto;

import java.util.ArrayList;

public interface DiscountService {
    ArrayList<DiscountDto> getDiscountList(String startDate, String endDate, String discountName);

    DiscountDto createDiscount(DiscountDto discountDto);
}
