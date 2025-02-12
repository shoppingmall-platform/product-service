package com.smplatform.product_service.domain.discount.service.impl;

import com.smplatform.product_service.domain.discount.dto.DiscountDto;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.discount.service.DiscountService;
import com.smplatform.product_service.exception.DiscountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    // 할인코드 목록 조회
    @Override
    public ArrayList<DiscountDto> getDiscountList(LocalDateTime startDate, LocalDateTime endDate, String discountName) {

        Optional<ArrayList<Discount>> discountList = Optional.of(new ArrayList<>());

        if (Objects.isNull(discountName)) {
            discountList = discountRepository.findByDiscountStartDateGreaterThanEqualAndDiscountEndDateLessThanEqual(startDate, endDate);
        } else {
            discountList = discountRepository.findByDiscountStartDateGreaterThanEqualAndDiscountEndDateLessThanEqualAndDiscountName(startDate, endDate, discountName);
        }
        if (discountList.isEmpty()) {
            throw new DiscountNotFoundException();
        }
        ArrayList<Discount> discountEntityList = discountList.get();

        ArrayList<DiscountDto> discountDtos = new ArrayList<>();

        for(Discount entity : discountEntityList) {
            discountDtos.add(DiscountDto.toDTO(entity));
        }

        return discountDtos;
    }

    // 할인코드 등록
    @Override
    public DiscountDto createDiscount(DiscountDto discountDto) {

        Discount entity = Discount.toEntity(discountDto);

        discountRepository.save(entity);

        return DiscountDto.toDTO(entity);
    }
}
