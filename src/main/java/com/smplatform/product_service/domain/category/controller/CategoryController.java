package com.smplatform.product_service.domain.category.controller;

import com.smplatform.product_service.domain.category.dto.CategoryRequestDto;
import com.smplatform.product_service.domain.category.dto.CategoryResponseDto;
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
    @Operation(summary = "카테고리 목록 조회", description = "전체 카테고리 조회")
    public ResponseEntity<List<CategoryResponseDto.CategoryInfo>> getCategoryList() {
        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    @GetMapping("/{category-id}")
    @Operation(summary = "특정 카테고리 조회", description = "특정 category id의 카테고리 조회")
    public ResponseEntity<CategoryResponseDto.CategoryInfo> getCategory(@PathVariable("category-id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    @Operation(summary = "카테고리 등록", description = "level : positive number")
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryRequestDto.CreateCategory body) {
        return ResponseEntity.status(201).body(categoryService.saveCategory(body));
    }

    @PostMapping("/update-category")
    @Operation(summary = "카테도리 수정", description = "")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryRequestDto.UpdateCategory body) {
        categoryService.updateCategory(body);
        return ResponseEntity.ok("update successful");
    }

    @PostMapping("/delete-category")
    @Operation(summary = "카테도리 삭제", description = "")
    public ResponseEntity<String> deleteCategory(@Valid @RequestBody CategoryRequestDto.DeleteCategory body) {
        categoryService.deleteCategory(body.getCategoryId());
        return ResponseEntity.status(204).build();
    }

}
