package com.smplatform.product_service.domain.discount.controller;

import com.smplatform.product_service.domain.discount.dto.DiscountRequestDto;
import com.smplatform.product_service.domain.discount.dto.DiscountResponseDto;
import com.smplatform.product_service.domain.discount.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/discounts")
@Tag(name = "Discount", description = "Discount management APIs")
@RequiredArgsConstructor
@Slf4j
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping("/getDiscountList")
    @Operation(summary = "할인코드 조회", description = "해당 조건의 할인코드 조회")
    public ResponseEntity<ArrayList<DiscountResponseDto.DiscountInfo>> getDiscountList(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate
                                                                                     , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
                                                                                     , @RequestParam(required = false) String discountName) {
        log.info("{}", 123123);
        ArrayList<DiscountResponseDto.DiscountInfo> discountInfo = discountService.getDiscountList(startDate, endDate, discountName);

        return ResponseEntity.status(HttpStatus.OK).body(discountInfo);
    }

    @PostMapping("/registerDiscount")
    @Operation(summary = "할인코드 등록", description = "해당 옵션의 할인코드 등록")
    public ResponseEntity<String> createDiscount(@RequestBody DiscountRequestDto.RegisterDiscount discountRequestDto) {

        String id = discountService.createDiscount(discountRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping("/deleteDiscount")
    @Operation(summary = "할인코드 삭제", description = "전달된 할인코드 삭제")
    public ResponseEntity<String> deleteDiscount(@RequestBody DiscountRequestDto.RegisterDiscount discountRequestDto) {

        String id = discountService.deleteDiscount(discountRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}

