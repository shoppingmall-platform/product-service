package com.smplatform.product_service.domain.product.controller;

import com.smplatform.product_service.annotation.AdminOnly;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Product", description = "Product management APIs")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * 제품 단일 조회
     *
     * @param productId
     * @return
     */
    @GetMapping("/products/{productId}")
    @Operation(summary = "product 조회", description = "해당 아이디의 제품 조회")
    public ResponseEntity<ProductResponseDto.GetProduct> getProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    /**
     * 제품 등록
     *
     * @param productDto
     * @return
     */
    @AdminOnly
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto.SaveProduct productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productDto));
    }

    /**
     * 제품 수정
     *
     * @param productDto
     * @return
     */
    @AdminOnly
    @PostMapping("/products/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto.UpdateProduct productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDto));
    }

    /**
     * 사용자용 상품 목록 조회
     *
     * @param pageable
     * @return
     */
    @GetMapping("/{category-id}/products")
    @Operation(summary = "사용자용 product 전체 조회 및 검색 조회", description = "사용자용 전체 제품 조회")
    public ResponseEntity<List<ProductResponseDto.GetProductForUsers>> getProductsForUsers(
            @PathVariable(name = "category-id") int categoryId,
            @RequestBody(required = false) ProductRequestDto.ProductSearchCondition condition,
            @PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsForUsers(categoryId, condition, pageable));
    }

    /**
     * 전체 태그 조회
     *
     * @return
     */
    @GetMapping("/products/tags")
    public ResponseEntity<List<ProductResponseDto.GetTag>> getProductsTags() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsTags());
    }

    /**
     * 관리자용 상품 목록 조회
     *
     * @param pageable
     * @return
     */
    @AdminOnly
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto.GetProduct>> getProducts(@RequestParam String search,
                                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(pageable));
    }
}
