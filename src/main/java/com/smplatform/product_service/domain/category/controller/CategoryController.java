package com.smplatform.product_service.domain.category.controller;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryInfo;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList() {

        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryInfo> getCategory(@PathVariable("category-id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryCreateDto body) {
        return ResponseEntity.status(201).body(categoryService.saveCategory(body));
    }

    @PostMapping("/update-category/{category-id}")
    public ResponseEntity<String> updateCategory(@PathVariable("category-id") int categoryId, @RequestBody CategoryUpdateDto body) {
        categoryService.updateCategory(categoryId, body);
        return ResponseEntity.ok("update successful");
    }

    @PostMapping("/delete-category/{category-id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("category-id") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(204).build();
    }


}
