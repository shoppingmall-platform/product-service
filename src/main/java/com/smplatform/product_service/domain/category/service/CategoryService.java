package com.smplatform.product_service.domain.category.service;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryInfo;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    CategoryInfo getCategoryById(int categoryId);

    int saveCategory(CategoryCreateDto body);

    void updateCategory(int categoryId, CategoryUpdateDto body);

    void deleteCategory(int categoryId);

}
