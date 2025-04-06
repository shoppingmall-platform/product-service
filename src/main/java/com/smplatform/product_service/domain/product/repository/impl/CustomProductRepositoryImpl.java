package com.smplatform.product_service.domain.product.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smplatform.product_service.domain.discount.entity.QDiscount;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.QProduct;
import com.smplatform.product_service.domain.product.entity.QProductTag;
import com.smplatform.product_service.domain.product.entity.QTag;
import com.smplatform.product_service.domain.product.repository.CustomProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Product> searchProducts(int categoryId, ProductRequestDto.ProductSearchCondition condition, Pageable pageable) {
        QProduct product = QProduct.product;
        QProductTag productTag = QProductTag.productTag;
        QTag tag = QTag.tag;
        QDiscount discount = QDiscount.discount;

        JPAQuery<Product> query = jpaQueryFactory
                .selectFrom(product)
                .leftJoin(product.discount).fetchJoin()
                .leftJoin(product.category).fetchJoin()
                .leftJoin(product.productTags, productTag)
                .leftJoin(productTag.tag, tag)
                .where(
                        product.category.categoryId.eq(categoryId),
                        buildCondition(product, tag, discount, condition)
                )
                .distinct();

        List<Product> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = query.stream().count();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression buildCondition(QProduct product, QTag tag, QDiscount discount,
                                             ProductRequestDto.ProductSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!Strings.isBlank(condition.getKeyword())) {
            builder.and(product.name.containsIgnoreCase(condition.getKeyword()));
        }
        if (condition.getIsSelling() != null) {
            builder.and(product.isSelling.eq(condition.getIsSelling()));
        }
        if (condition.getIsDeleted() != null) {
            builder.and(product.isDeleted.eq(condition.getIsDeleted()));
        }
        if (!Strings.isBlank(condition.getTagName())) {
            builder.and(tag.tagName.eq(condition.getTagName()));
        }
        if (!Strings.isBlank(condition.getDiscountName())) {
            builder.and(discount.discountName.eq(condition.getDiscountName()));
        }
        if (condition.getStartDate() != null && condition.getEndDate() != null) {
            builder.and(product.createdAt.between(condition.getStartDate(), condition.getEndDate()));
        }

        return builder.hasValue() ? (BooleanExpression) builder.getValue() : null;
    }

}
