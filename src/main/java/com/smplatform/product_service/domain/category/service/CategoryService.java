package com.smplatform.product_service.domain.category.service;

import com.smplatform.product_service.domain.category.dto.CategoryRequestDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto.CategoryInfo> getCategoryList();

    CategoryResponseDto.CategoryInfo getCategoryById(int categoryId);

    int saveCategory(CategoryRequestDto.CreateCategory body);

    void updateCategory(CategoryRequestDto.UpdateCategory body);

    void deleteCategory(int categoryId);

}
