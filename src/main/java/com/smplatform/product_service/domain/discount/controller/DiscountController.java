package com.smplatform.product_service.domain.discount.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.discount.dto.DiscountDto;
import com.smplatform.product_service.domain.discount.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/discounts")
@Tag(name = "Discount", description = "Discount management APIs")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/discountList")
    @Operation(summary = "할인코드 조회", description = "해당 조건의 할인코드 조회")
    public ResponseEntity<ArrayList<DiscountDto>> getDiscountList(@RequestParam String startDate
                                                    , @RequestParam String endDate
                                                    , @RequestParam(required = false) String discountName) {
        ArrayList<DiscountDto> discountDto = discountService.getDiscountList(startDate, endDate, discountName);

        return ResponseEntity.status(HttpStatus.OK).body(discountDto);
    }

    @AdminOnly
    @PostMapping("/discount")
    @Operation(summary = "할인코드 등록", description = "할인코드 등록")
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto) {

        DiscountDto dto = discountService.createDiscount(discountDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}

