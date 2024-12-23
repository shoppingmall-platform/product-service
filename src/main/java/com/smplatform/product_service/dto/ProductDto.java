package com.smplatform.product_service.dto;

import com.smplatform.product_service.domain.Category;
import com.smplatform.product_service.domain.Discount;
import com.smplatform.product_service.domain.ProductState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private boolean isDeleted;
    private Category category;
    private ProductState productState;
    private boolean isSelling;
    private LocalDateTime createdAt;
    private int price;
    private Discount discount;
    private String summaryDescription;
    private String simpleDescription;
}
