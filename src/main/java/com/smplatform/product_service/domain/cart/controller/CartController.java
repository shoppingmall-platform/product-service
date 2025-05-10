package com.smplatform.product_service.domain.cart.controller;

import com.smplatform.product_service.domain.cart.dto.CartRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartResponseDto;
import com.smplatform.product_service.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    @Operation(summary = "장바구니에 제품 추가", description = "장바구니는 기본으로 1회원 1장바구니. 해당 api 는 장바구니에 제품을 추가하기위한 api.")
    public ResponseEntity<String> addCart(@RequestHeader(name = "X-MEMBER-ID") String memberId,
                                          @RequestBody CartRequestDto.CartAdd requestDto) {
        return null;
    }

    @GetMapping
    @Operation(summary = "장바구니 조회", description = "장바구니는 기본으로 1회원 1장바구니. 해당 api 는 장바구니에 제품을 추가하기위한 api.")
    public ResponseEntity<CartResponseDto.CartGet> getCart(@RequestHeader(name = "X-MEMBER-ID") String memberId) {
        return null;
    }

    @PostMapping("/{cart-id}")
    @Operation(summary = "장바구니 수정", description = "장바구니 제품 ")
    public ResponseEntity<String> updateCart(@RequestHeader(name = "X-MEMBER-ID") String memberId,
                                             @PathVariable(name = "cart-id") Long cartId,
                                             @RequestBody CartRequestDto.CartUpdate requestDto) {
        return null;
    }

}
