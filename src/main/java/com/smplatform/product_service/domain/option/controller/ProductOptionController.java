package com.smplatform.product_service.domain.option.controller;

import com.smplatform.product_service.domain.option.dto.ProductOptionRequestDto;
import com.smplatform.product_service.domain.option.dto.ProductOptionResponseDto;
import com.smplatform.product_service.domain.option.entity.ProductOption;
import com.smplatform.product_service.domain.option.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    @RequestMapping("/product-option")
    public ResponseEntity<Integer> creatProductOption(ProductOptionRequestDto.SaveProductOption productOptionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productOptionService.saveProductoption(productOptionRequestDto));
    }
}
