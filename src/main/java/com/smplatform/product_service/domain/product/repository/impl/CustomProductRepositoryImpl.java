package com.smplatform.product_service.domain.product.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smplatform.product_service.domain.discount.entity.QDiscount;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.entity.*;
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
    public Page<Product> searchProducts(ProductRequestDto.AdminProductSearchCondition condition, Pageable pageable) {
        QProduct product = QProduct.product;
        QProductTag productTag = QProductTag.productTag;
        QTag tag = QTag.tag;
        QDiscount discount = QDiscount.discount;
        QProductCategoryMapping productCategoryMapping = QProductCategoryMapping.productCategoryMapping;

        JPAQuery<Product> query = jpaQueryFactory
                .selectFrom(product)
                .leftJoin(product.discount).fetchJoin()
                .leftJoin(product.productTags, productTag)
                .leftJoin(productTag.tag, tag)
                .leftJoin(productCategoryMapping).on(productCategoryMapping.product.eq(product))
                .where(
                        condition.getCategoryId() != null ? productCategoryMapping.category.categoryId.eq(condition.getCategoryId()) : null,
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

    @Override
    public Page<Product> searchUserProducts(int categoryId, ProductRequestDto.UserProductSearchCondition condition, Pageable pageable) {
        QProduct product = QProduct.product;
        QProductTag productTag = QProductTag.productTag;
        QTag tag = QTag.tag;
        QDiscount discount = QDiscount.discount;
        QProductCategoryMapping productCategoryMapping = QProductCategoryMapping.productCategoryMapping;

        JPAQuery<Product> query = jpaQueryFactory
                .selectFrom(product)
                .leftJoin(product.discount).fetchJoin()
                .leftJoin(product.productTags, productTag)
                .leftJoin(productTag.tag, tag)
                .leftJoin(productCategoryMapping).on(productCategoryMapping.product.eq(product))
                .where(
                        productCategoryMapping.category.categoryId.eq(categoryId),
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

    @Override
    public Page<Product> findAllByCategoryIds(List<ProductCategoryMapping> productCategoryMappings) {

        return null;
    }

    private BooleanExpression buildCondition(QProduct product, QTag tag, QDiscount discount,
                                             ProductRequestDto.ProductSearchConditions condition) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!Strings.isBlank(condition.getKeyword())) {
            builder.and(product.name.containsIgnoreCase(condition.getKeyword()));
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
        if (condition.getMinimumPrice() > 0) {
            builder.and(product.price.goe(condition.getMinimumPrice()));
        }
        if (condition.getMaximumPrice() > 0 && condition.getMaximumPrice() >= condition.getMinimumPrice()) {
            builder.and(product.price.loe(condition.getMaximumPrice()));
        }

        if (condition instanceof ProductRequestDto.AdminProductSearchCondition adminProductSearchCondition) {
            if (adminProductSearchCondition.getIsSelling() != null) {
                builder.and(product.isSelling.eq(adminProductSearchCondition.getIsSelling()));
            }
            if (adminProductSearchCondition.getIsDeleted() != null) {
                builder.and(product.isDeleted.eq(adminProductSearchCondition.getIsDeleted()));
            }
        }

        return builder.hasValue() ? (BooleanExpression) builder.getValue() : null;
    }

}
