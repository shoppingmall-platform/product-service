package com.smplatform.product_service.domain.category.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateDto {
    private Integer categoryParent;
    @NotEmpty
    private String categoryName;
    @Positive
    private int categoryLevel;

    public Category toCategory() {
        return Category.builder()
                .categoryName(this.categoryName)
                .categoryLevel(this.categoryLevel)
                .parentCategory(categoryParent == null ? null : Category.builder().categoryId(categoryParent).build())
                .build();
    }
}
