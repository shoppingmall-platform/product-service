package com.smplatform.product_service.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private int categoryLevel;

    @JoinColumn(name = "parent_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> children;

    @Builder
    public Category(int categoryId, String categoryName, int categoryLevel, Category parentCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryLevel = categoryLevel;
        this.parentCategory = parentCategory;
    }

    public Category update(String categoryName, int categoryLevel, Category parentCategory) {
        this.categoryName = categoryName;
        this.categoryLevel = categoryLevel;
        this.parentCategory = parentCategory;
        return this;
    }
}
