package com.smplatform.product_service.domain.product.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Tag(name = "Product", description = "Product management APIs")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    @Operation(summary = "product 조회", description = "해당 아이디의 제품 조회")
    public ResponseEntity<ProductResponseDto.GetProduct> getProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @AdminOnly
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto.SaveProduct productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productDto));
    }

    @AdminOnly
    @PostMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto.UpdateProduct productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto.GetProduct>> getProducts(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(pageable));
    }
}
