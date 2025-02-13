package com.smplatform.product_service.domain.product.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@Tag(name = "Product", description = "Product management APIs")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    @Operation(summary = "product 조회", description = "해당 아이디의 제품 조회")
    public ResponseEntity<ProductRequestDto> getProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @AdminOnly
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productDto));
    }

    @AdminOnly
    @PostMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productRequestDto));
    }
}
