package com.smplatform.product_service.domain.category.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private int categoryId;

    public CategoryResponseDto(int categoryId) {
        this.categoryId = categoryId;
    }

    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto( category.getCategoryId() );
    }
}
