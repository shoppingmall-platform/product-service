package com.smplatform.product_service.domain.cart.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;
import com.smplatform.product_service.domain.cart.entity.QCartItem;
import com.smplatform.product_service.domain.cart.repository.CustomCartItemRepository;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.entity.QMember;
import com.smplatform.product_service.domain.discount.entity.QDiscount;
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
        QDiscount discount = QDiscount.discount;

        return jpaQueryFactory
                .select(Projections.constructor(CartItemResponseDto.CartItemFlatDto.class,
                        cartItem.cartItemId,
                        cartItem.quantity,

                        productOption.productOptionId,
                        productOption.productOptionName,
                        productOption.stockQuantity,
                        productOption.additionalPrice,

                        productOptionDetail.productOptionType,
                        productOptionDetail.productOptionDetailName,

                        product.productId,
                        product.name,
                        product.price,
                        product.thumbnailPath,

                        discount.discountType,
                        discount.discountValue,
                        discount.discountStartDate,
                        discount.discountEndDate
                ))
                .from(cartItem)
                .join(cartItem.productOption, productOption)
                .join(productOption.product, product)
                .leftJoin(product.discount, discount)
                .leftJoin(productOptionDetail).on(productOptionDetail.productOption.eq(productOption))
                .where(cartItem.member.memberId.eq(memberId))
                .fetch();
    }

    @Override
    public void deleteAllByMemberAndCartItemIdIn(String memberId, List<Long> list) {
        QCartItem cartItem = QCartItem.cartItem;

        jpaQueryFactory
                .delete(cartItem)
                .where(cartItem.member.memberId.eq(memberId)
                        .and(cartItem.cartItemId.in(list)))
                .execute();
    }
}
