package com.smplatform.product_service.domain.cart.controller;

import com.smplatform.product_service.domain.cart.dto.CartRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartResponseDto;
import com.smplatform.product_service.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/members/cart")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    @Operation(summary = "장바구니에 제품 추가", description = "장바구니는 기본으로 1회원 1장바구니. 해당 api 는 장바구니에 제품을 추가하기위한 api.")
    public ResponseEntity<String> addCartItem(@RequestHeader(name = "X-MEMBER-ID") String memberId,
                                          @RequestBody CartRequestDto.CartAdd requestDto) {
        return null;
    }

    @GetMapping
    @Operation(summary = "장바구니 조회", description = "장바구니는 기본으로 1회원 1장바구니. 해당 api 는 장바구니에 제품을 추가하기위한 api.")
    public ResponseEntity<CartResponseDto.CartGet> getCartItems(@RequestHeader(name = "X-MEMBER-ID") String memberId) {
        return null;
    }

    @PostMapping("/option-update")
    @Operation(summary = "장바구니 수정", description = "장바구니 제품 옵션 수정을 위한 api")
    public ResponseEntity<String> updateCartItem(@RequestHeader(name = "X-MEMBER-ID") String memberId,
                                             @RequestBody CartRequestDto.CartUpdate requestDto) {
        return null;
    }

    @PostMapping("/delete")
    @Operation(summary = "장바구니 삭제", description = "장바구니 제품 삭제를 위한 api db에서 제거됨")
    public ResponseEntity<String> deleteCartItems(@RequestHeader(name = "X-MEMBER-ID") String memberId,
                                             @RequestBody CartRequestDto.CartDelete requestDto) {
        return null;
    }

}
