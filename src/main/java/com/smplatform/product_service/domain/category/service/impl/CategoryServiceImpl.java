package com.smplatform.product_service.domain.category.service.impl;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryInfo;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.exception.CategoryNotFoundException;
import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfo getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException(categoryId));

        return CategoryInfo.of(category);
    }

    @Override
    public int saveCategory(CategoryCreateDto body) {
        Category category = body.toEntity();
        if (body.getCategoryParentId() != null) {
            Category parentCategory = categoryRepository.findById(body.getCategoryParentId()).orElseThrow(()-> new CategoryNotFoundException(body.getCategoryParentId()));
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
        return category.getCategoryId();
    }

    @Override
    public void updateCategory(int categoryId, CategoryUpdateDto body) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        String newName = body.getCategoryName();
        Integer newLevel = body.getCategoryLevel();
        if (Objects.nonNull(newName) && !newName.isEmpty()) {
            category.setCategoryName(body.getCategoryName());
        }
        if (Objects.nonNull(newLevel) && newLevel > 0) {
            category.setCategoryLevel(body.getCategoryLevel());
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.clearParentCategory(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
