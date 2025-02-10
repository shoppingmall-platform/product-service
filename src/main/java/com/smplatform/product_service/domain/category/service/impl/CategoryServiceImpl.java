package com.smplatform.product_service.domain.category.service.impl;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.exception.CategoryNotFoundException;
import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("존재하지 않는 카테고리 ID 입니다 : "+id));
    }

    @Override
    public CategoryResponseDto saveCategory(CategoryCreateDto body) {
        Category category = body.toCategory();
        categoryRepository.save(category);
        return CategoryResponseDto.of(category);
    }

    @Override
    public void updateCategory(long categoryId, CategoryUpdateDto body) {

    }

    @Override
    public void deleteCategory(long categoryId) {

    }
}
