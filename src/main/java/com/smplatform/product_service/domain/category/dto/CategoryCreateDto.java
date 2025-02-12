package com.smplatform.product_service.domain.category.dto;

import com.smplatform.product_service.domain.category.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateDto {
    private Integer categoryParentId;
    @NotEmpty
    private String categoryName;
    @Positive
    private int categoryLevel;

    public Category toEntity() {
        return Category.builder()
                .categoryName(this.categoryName)
                .categoryLevel(this.categoryLevel)
                .build();
    }
}
