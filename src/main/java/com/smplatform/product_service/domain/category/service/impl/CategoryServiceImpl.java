package com.smplatform.product_service.domain.category.service.impl;

import com.smplatform.product_service.domain.category.dto.CategoryRequestDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.exception.InvalidCategoryLevelException;
import com.smplatform.product_service.domain.category.exception.CategoryNotFoundException;
import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponseDto.CategoryInfo> getCategoryList() {
        return categoryRepository.findAll().stream().map(c-> CategoryResponseDto.CategoryInfo.of(c)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponseDto.CategoryInfo getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException(categoryId));

        return CategoryResponseDto.CategoryInfo.of(category);
    }

    @Override
    public int saveCategory(CategoryRequestDto.CreateCategory body) {
        Category category = body.toEntity();
        if (body.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(body.getParentCategoryId()).orElseThrow(()-> new CategoryNotFoundException("존재하지 않는 부모 Category Id 입니다 : "+body.getParentCategoryId()));
            category.setParentCategory(parentCategory);
        }
        checkCategoryValidity(category);
        categoryRepository.save(category);
        return category.getCategoryId();
    }

    @Override
    public void updateCategory(CategoryRequestDto.UpdateCategory body) {
        Category category = categoryRepository.findById(body.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(body.getCategoryId()));

        Category parentCategory = body.getParentCategoryId() == null? null :
                categoryRepository.findById(body.getParentCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("존재하지 않는 부모 Category Id 입니다 : "+body.getParentCategoryId()));

        category.update(body.getCategoryName(), body.getCategoryLevel(), parentCategory);
        checkCategoryValidity(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);

    }

    private void checkCategoryValidity(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }

        int level = category.getCategoryLevel();
        Category parentCategory = category.getParentCategory();

        if (level <0 || level > 3) {
            throw new InvalidCategoryLevelException("level 범위 : 1~3 (1=대, 2=중, 3=소). level: " + level);
        }

        if (parentCategory != null) {
            if (level < parentCategory.getCategoryLevel() || level - parentCategory.getCategoryLevel() != 1) {
                throw new InvalidCategoryLevelException("부모 카테고리보다 한단계 아래만 가능합니다. level: " + level + ", parent-level: " + parentCategory.getCategoryLevel());
            }

        } else if (level > 1) {
            throw new InvalidCategoryLevelException("부모 카테고리 값이 필요합니다.");
        }
    }



}
