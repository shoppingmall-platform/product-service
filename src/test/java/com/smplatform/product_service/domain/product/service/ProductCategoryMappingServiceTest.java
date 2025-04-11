package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.ProductCategoryMapping;
import com.smplatform.product_service.domain.product.repository.ProductCategoryMappingRepository;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class ProductCategoryMappingServiceTest {
    @Autowired
    private ProductCategoryMappingService productCategoryMappingService;

    @Autowired
    private ProductCategoryMappingRepository productCategoryMappingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("카테고리 ID로 상품 조회 시 하위 카테고리 상품까지 모두 조회 가능")
    void getProductsByCategoryId_returnsProducts() {
        // given
        Category topCategory = Category.builder().categoryName("의류").categoryLevel(1).build();
        Category midCategory = Category.builder().categoryName("상의").categoryLevel(2).parentCategory(topCategory).build();
        Category btmCategory = Category.builder().categoryName("민소매").categoryLevel(3).parentCategory(midCategory).build();
        categoryRepository.saveAll(List.of(topCategory, midCategory, btmCategory));

        Product topCategoryProduct = Product.builder().name("청바지").price(30000).build();
        Product midCategoryProduct = Product.builder().name("레인보우자켓").price(30000).build();
        Product btmCategoryProduct = Product.builder().name("검정나시").price(30000).build();
        productRepository.saveAll(List.of(topCategoryProduct, midCategoryProduct, btmCategoryProduct));

        productCategoryMappingService.save(topCategory.getCategoryId(), topCategoryProduct.getId());
        productCategoryMappingService.save(midCategory.getCategoryId(), midCategoryProduct.getId());
        productCategoryMappingService.save(btmCategory.getCategoryId(), btmCategoryProduct.getId());

        // when
        List<ProductResponseDto.ProductGet> resultTop = productCategoryMappingService.getProductsByCategoryId(topCategory.getCategoryId());    // 모든 상품
        List<ProductResponseDto.ProductGet> resultMid = productCategoryMappingService.getProductsByCategoryId(midCategory.getCategoryId());    // 중분류 이하 상품
        List<ProductResponseDto.ProductGet> resultBtm = productCategoryMappingService.getProductsByCategoryId(btmCategory.getCategoryId());    // 하위 상품

        // then
        assertEquals(3, resultTop.size());
        assertEquals(2, resultMid.size());
        assertEquals(1, resultBtm.size());
    }

}