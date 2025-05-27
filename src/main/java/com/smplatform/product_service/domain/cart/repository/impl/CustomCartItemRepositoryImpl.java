package com.smplatform.product_service.domain.cart.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;
import com.smplatform.product_service.domain.cart.entity.QCartItem;
import com.smplatform.product_service.domain.cart.repository.CustomCartItemRepository;
import com.smplatform.product_service.domain.product.entity.QProduct;
import com.smplatform.product_service.domain.product.entity.QProductOption;
import com.smplatform.product_service.domain.product.entity.QProductOptionDetail;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomCartItemRepositoryImpl implements CustomCartItemRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CartItemResponseDto.CartItemFlatDto> findAllByMemberId(String memberId) {
        QCartItem cartItem = QCartItem.cartItem;
        QProductOption productOption = QProductOption.productOption;
        QProductOptionDetail productOptionDetail = QProductOptionDetail.productOptionDetail;
        QProduct product = QProduct.product;

        return jpaQueryFactory
                .select(Projections.constructor(
                        CartItemResponseDto.CartItemFlatDto.class,
                        cartItem.cartItemId,
                        cartItem.quantity,
                        productOption.productOptionId,
                        productOption.productOptionName,
                        productOption.stockQuantity,
                        productOption.additionalPrice,
                        productOptionDetail.productOptionType,
                        productOptionDetail.productOptionDetailName,
                        product.id,
                        product.name,
                        product.price,
                        product.thumbnailPath
                ))
                .from(cartItem)
                .join(cartItem.productOption, productOption)
                .join(productOption.product, product)
                .leftJoin(productOptionDetail).on(productOptionDetail.productOption.eq(productOption))
                .where(cartItem.member.memberId.eq(memberId))
                .fetch();
    }
}
