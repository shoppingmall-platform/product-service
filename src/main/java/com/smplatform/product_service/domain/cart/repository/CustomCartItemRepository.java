package com.smplatform.product_service.domain.cart.repository;

import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;
import com.smplatform.product_service.domain.member.entity.Member;

import java.util.List;

public interface CustomCartItemRepository {

    List<CartItemResponseDto.CartItemFlatDto> findAllByMemberId(String memberId);

    void deleteAllByMemberAndCartItemIdIn(String memberId, List<Long> list);
}
