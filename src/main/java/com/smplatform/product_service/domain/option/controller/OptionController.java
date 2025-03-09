package com.smplatform.product_service.domain.option.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.option.dto.OptionRequestDto;
import com.smplatform.product_service.domain.option.dto.OptionResponseDto;
import com.smplatform.product_service.domain.option.service.OptionService;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<Long> createOption(@RequestBody OptionRequestDto.SaveOption body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(optionService.saveOption(body));
    }

    @GetMapping
    public ResponseEntity<List<OptionResponseDto.GetOption>> getOption(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(optionService.getOptions(pageable));
    }

    @AdminOnly
    @PostMapping("/delete-option")
    public ResponseEntity<Void> updateProduct(@RequestBody OptionRequestDto.DeleteOption deleteOptionDto) {
        optionService.deleteOptionType(deleteOptionDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
