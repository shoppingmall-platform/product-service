package com.smplatform.product_service.domain.option.controller;

import com.smplatform.product_service.domain.option.dto.ProductOptionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class OptionController {

    @PostMapping("/option")
    public ResponseEntity<Integer> createOption(ProductOptionRequestDto.SaveProductOption productOptionRequestDto) {
        return null;
    }
}
