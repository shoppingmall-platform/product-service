package com.smplatform.product_service.domain.category.repository;

import com.smplatform.product_service.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Modifying
    @Query("DELETE FROM Category c WHERE c.parentCategory.categoryId = :parentId")
    void deleteByParentCategory(@Param("parentId") int parentId);
}
