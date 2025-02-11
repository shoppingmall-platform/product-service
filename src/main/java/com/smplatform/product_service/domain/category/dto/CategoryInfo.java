package com.smplatform.product_service.domain.category.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryInfo {
    private int categoryId;
    private String categoryName;
    private int categoryLevel;
    private Integer parentCategoryId;

    public static CategoryInfo of(Category category) {
        Category parentCategory = category.getParentCategory();
        return new CategoryInfo(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryLevel(),
                parentCategory == null ? null : parentCategory.getCategoryId()
        );
    }
}
