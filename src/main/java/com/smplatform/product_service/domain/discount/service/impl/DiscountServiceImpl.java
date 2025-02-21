package com.smplatform.product_service.domain.discount.service.impl;

import com.smplatform.product_service.domain.discount.dto.DiscountRequestDto;
import com.smplatform.product_service.domain.discount.dto.DiscountResponseDto;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.discount.exception.DiscountNotFoundException;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.discount.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    // 할인코드 목록 조회
    @Override
    public List<DiscountResponseDto.DiscountInfo> getDiscountList(String referenceDate, LocalDateTime startDate, LocalDateTime endDate, String discountName) {
        List<Discount> discountEntityList = discountRepository.findDiscounts(referenceDate, startDate, endDate, discountName);

        return discountEntityList.stream()
                .map(DiscountResponseDto.DiscountInfo::of)
                .toList();
    }

    // 할인코드 등록
    @Override
    public String createDiscount(DiscountRequestDto.RegisterDiscount discountRequestDto) {

        Discount entity = discountRequestDto.toEntity();

        discountRepository.save(entity);

        return String.valueOf(entity.getDiscountId());
    }

    // 할인코드 삭제
    @Override
    public String deleteDiscount(DiscountRequestDto.DeleteDiscount discountRequestDto) {

        int discountId = discountRequestDto.getDiscountId();

        if (!discountRepository.existsById(discountId)) {
            throw new DiscountNotFoundException("삭제할 할인 코드가 존재하지 않습니다. ID: " + discountId);
        }

        discountRepository.deleteById(discountId);

        return String.valueOf(discountId);
    }
}
