package com.smplatform.product_service.domain.category.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CategoryUpdateDto {
    @NotEmpty
    private String categoryName;

    @Positive
    private Integer categoryLevel;
}
