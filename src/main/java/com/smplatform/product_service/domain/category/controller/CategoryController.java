package com.smplatform.product_service.domain.category.controller;

import com.smplatform.product_service.domain.category.dto.CategoryCreateDto;
import com.smplatform.product_service.domain.category.dto.CategoryInfo;
import com.smplatform.product_service.domain.category.dto.CategoryUpdateDto;
import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "카테도리 목록 조회", description = "전체 카테고리 조회")
    public ResponseEntity<List<Category>> getCategoryList() {

        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    @GetMapping("/{category-id}")
    @Operation(summary = "특정 카테도리 조회", description = "특정 category id의 카테고리 조회")
    public ResponseEntity<CategoryInfo> getCategory(@PathVariable("category-id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    @Operation(summary = "카테도리 등록", description = "level : positive number")
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryCreateDto body) {
        return ResponseEntity.status(201).body(categoryService.saveCategory(body));
    }

    @PostMapping("/update-category/{category-id}")
    @Operation(summary = "카테도리 수정", description = "")
    public ResponseEntity<String> updateCategory(@PathVariable("category-id") int categoryId, @RequestBody CategoryUpdateDto body) {
        categoryService.updateCategory(categoryId, body);
        return ResponseEntity.ok("update successful");
    }

    @PostMapping("/delete-category/{category-id}")
    @Operation(summary = "카테도리 삭제", description = "")
    public ResponseEntity<String> deleteCategory(@PathVariable("category-id") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(204).build();
    }


}
