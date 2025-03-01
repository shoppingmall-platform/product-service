package com.smplatform.product_service.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smplatform.product_service.domain.category.entity.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public class CategoryRequestDto {

    private CategoryRequestDto() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    public static class CreateCategory {
        private Integer parentCategoryId;
        @NotEmpty
        private String categoryName;
        @Min(value = 1, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        @Max(value = 3, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        private int categoryLevel;

        public Category toEntity() {
            return Category.builder()
                    .categoryName(this.categoryName)
                    .categoryLevel(this.categoryLevel)
                    .build();
        }
    }

    @Getter
    public static class UpdateCategory {
        private int categoryId;

        private Integer parentCategoryId;

        @NotEmpty
        private String categoryName;

        @Min(value = 1, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        @Max(value = 3, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        private Integer categoryLevel;
    }

    @Getter
    public static class DeleteCategory {
        private int categoryId;
    }

    public class CategoryInfo {
        @JsonProperty("category_id")
        private int categoryId;
        @JsonProperty("parent_category_id")
        private Integer parentCategoryId;
        @NotEmpty
        @JsonProperty("category_name")
        private String categoryName;
        @Min(value = 1, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        @Max(value = 3, message = "level 범위 : 1~3 (1=대, 2=중, 3=소)")
        @JsonProperty("category_level")
        private int categoryLevel;

        public Category toEntity() {
            return Category.builder()
                    .categoryId(this.categoryId)
                    .categoryName(this.categoryName)
                    .categoryLevel(this.categoryLevel)
                    .build();
        }
    }
}
