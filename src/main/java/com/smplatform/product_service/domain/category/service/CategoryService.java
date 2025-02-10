package com.smplatform.product_service.domain.category.service;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    Category getCategoryById(int id);

    CategoryResponseDto saveCategory(CategoryCreateDto body);

    void updateCategory(long categoryId, CategoryUpdateDto body);

    void deleteCategory(long categoryId);

}
