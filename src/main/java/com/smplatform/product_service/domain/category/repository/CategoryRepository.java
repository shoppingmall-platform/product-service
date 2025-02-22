package com.smplatform.product_service.domain.category.repository;

import com.smplatform.product_service.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
