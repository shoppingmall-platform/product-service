package com.smplatform.product_service.domain.category.service.impl;

import com.smplatform.product_service.domain.category.dto.CategoryRequestDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.exception.CategoryLevelOutOfRangeException;
import com.smplatform.product_service.domain.category.exception.CategoryNotFoundException;
import com.smplatform.product_service.domain.category.exception.InvalidCategoryNameException;
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
        categoryRepository.save(category);
        return category.getCategoryId();
    }

    @Override
    public void updateCategory(CategoryRequestDto.UpdateCategory body) {
        Category category = categoryRepository.findById(body.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(body.getCategoryId()));

        String newName = body.getCategoryName();
        Integer newLevel = body.getCategoryLevel();

        if (Objects.nonNull(newName)) {
            if (!newName.trim().isEmpty()) {
                category.setCategoryName(body.getCategoryName());
            } else {
                throw new InvalidCategoryNameException("Empty name");
            }
        }

        if (Objects.nonNull(newLevel)){
            if (newLevel > 0 && newLevel < 4) {
                category.setCategoryLevel(body.getCategoryLevel());
            } else {
                throw new CategoryLevelOutOfRangeException(newLevel);
            }
        }

    }

    @Override
    public void deleteCategory(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.clearParentCategory(categoryId);
            categoryRepository.deleteById(categoryId);
        }
    }
}
