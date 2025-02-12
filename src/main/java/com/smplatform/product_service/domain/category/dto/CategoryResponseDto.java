package com.smplatform.product_service.domain.category.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CategoryResponseDto {

    private CategoryResponseDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CategoryInfo {
        private int categoryId;
        private String categoryName;
        private int categoryLevel;
        private Integer parentCategoryId;

        public static CategoryInfo of(Category category) {
            Category parentCategory = category.getParentCategory();
            return CategoryInfo.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .categoryLevel(category.getCategoryLevel())
                    .parentCategoryId(parentCategory == null ? null : parentCategory.getCategoryId())
                    .build()
                    ;
        }
    }

}
