package com.smplatform.product_service.domain.product.dto;

import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.product.entity.*;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRequestDto {


    @Getter
    public static class ProductSave {
        private String name;
        private String description;
        private int categoryId;
        private int price;
        private String summaryDescription;
        private String simpleDescription;
        private List<ProductOptionSave> productOptions;
        private String thumbnail;
        private ProductImageRequestDto.ProductImageSave productImages;
        private List<TagSave> tags;

        public Product toEntity() {
            return Product.builder()
                    .id(0)
                    .name(this.getName())
                    .description(this.getDescription())
                    .price(this.getPrice())
                    .summaryDescription(this.getSummaryDescription())
                    .simpleDescription(this.getSimpleDescription())
                    .thumbnailPath(this.thumbnail)
                    .build();
        }
    }

    @Getter
    public static class TagSave {
        private String tagName;

        public Tag toEntity() {
            return new Tag(0L, tagName);
        }
    }

    @Getter
    public static class ProductOptionSave {
        private String productOptionName;
        private List<SaveProductOptionDetail> productOptionDetails;
        private int stockQuantity;
        private int additionalPrice;

        public ProductOption toEntity() {
            return ProductOption.builder()
                    .productOptionName(this.productOptionName)
                    .createdAt(LocalDateTime.now())
                    .stockQuantity(this.stockQuantity)
                    .additionalPrice(this.additionalPrice)
                    .build();
        }
    }

    @Getter
    public static class SaveProductOptionDetail {
        private String productOptionType;
        private String productOptionDetailName;

        public ProductOptionDetail toEntity() {
            return ProductOptionDetail.builder()
                    .productOptionDetailName(this.productOptionDetailName)
                    .productOptionType(this.productOptionType)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ProductUpdate {
        private long id;
        private String name;
        private String description;
        private int categoryId;
        private ProductState productState;
        private int price;
        private Integer discountId;
        private String summaryDescription;
        private String simpleDescription;
        private String thumbnailPath;
        private ProductImageRequestDto.ProductImageSave productImagePaths;
    }

    @Getter
    @ToString
    public abstract static class ProductSearchConditions {
        protected String keyword;
        protected LocalDateTime startDate;
        protected LocalDateTime endDate;
        protected String tagName;
        protected String discountName;
        protected Integer minimumPrice;
        protected Integer maximumPrice;

        public boolean isConditionEmpty() {
            try {
                Class<?> currentClass = this.getClass();
                while (currentClass != null && ProductSearchConditions.class.isAssignableFrom(currentClass)) {
                    for (Field field : currentClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.get(this) != null) return false;
                    }
                    currentClass = currentClass.getSuperclass();
                }
                return true;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("필드 접근 중 오류", e);
            }
        }
    }

    @Getter
    @ToString
    public static class UserProductSearchCondition extends ProductSearchConditions {
    }

    @Getter
    @ToString
    public static class AdminProductSearchCondition extends ProductSearchConditions {
        private Integer categoryId;
        private Boolean isSelling;
        private Boolean isDeleted;
    }
}
