package com.smplatform.product_service.domain.product.dto;

import com.smplatform.product_service.domain.ProductState;
import com.smplatform.product_service.domain.product.entity.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class ProductRequestDto {

    @Getter
    public static class SaveProduct {
        private String name;
        private String description;
        private int categoryId;
        private int price;
        private String summaryDescription;
        private String simpleDescription;
        private List<SaveProductOption> productOptions;
        private String thumbnail;
        private ProductImageRequestDto.SaveProductImage productImages;
        private List<ProductRequestDto.SaveTag> tags;

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
    public static class SaveTag {
        private String tagName;

        public Tag toEntity() {
            return new Tag(0L, tagName);
        }
    }

    @Getter
    public static class SaveProductOption {
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
    public static class UpdateProduct {
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
        private ProductImageRequestDto.SaveProductImage productImagePaths;
    }
}
