package com.smplatform.product_service.controller;

import com.smplatform.product_service.dto.ProductDto;
import com.smplatform.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/v1")
@Tag(name = "Product", description = "Product management APIs")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    @Operation(summary = "product 조회", description = "해당 아이디의 제품 조회")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productDto));
    }
}

