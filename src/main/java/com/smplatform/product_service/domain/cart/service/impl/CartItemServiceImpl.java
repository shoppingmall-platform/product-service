package com.smplatform.product_service.domain.cart.service.impl;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;
import com.smplatform.product_service.domain.cart.entity.CartItem;
import com.smplatform.product_service.domain.cart.repository.CartItemRepository;
import com.smplatform.product_service.domain.cart.repository.CustomCartItemRepository;
import com.smplatform.product_service.domain.cart.service.CartItemService;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.exception.MemberNotFoundException;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import com.smplatform.product_service.domain.product.repository.ProductOptionDetailRepository;
import com.smplatform.product_service.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionDetailRepository productOptionDetailRepository;

    @Override
    public String addCartItems(String memberId, CartItemRequestDto.CartAdd requestDto) {
        return "";
    }

    @Override
    public CartItemResponseDto.CartGet getCartItems(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(String.format("멤버 %s 를 찾을 수 없습니다", memberId)));
        List<CartItemResponseDto.CartItemFlatDto> allCartItemsByMemberId = cartItemRepository.findAllByMemberId(member.getMemberId());
        log.error("페치 결과 : {}", allCartItemsByMemberId);

        return new CartItemResponseDto.CartGet(allCartItemsByMemberId.stream().map(dto -> {
            CartItemResponseDto.ProductOptionDetailGet productOptionDetailGet = CartItemResponseDto.ProductOptionDetailGet.builder()
                    .productOptionType(dto.getProductOptionType())
                    .productOptionDetailName(dto.getProductOptionDetailName())
                    .build();
            CartItemResponseDto.ProductOptionGet productOptionGet = CartItemResponseDto.ProductOptionGet.builder()
                    .productOptionId(dto.getProductOptionId())
                    .productOptionName(dto.getProductOptionName())
                    .stockQuantity(dto.getStockQuantity())
                    .additionalPrice(dto.getAdditionalPrice())
                    .build();

        }).toList());
    }
}
