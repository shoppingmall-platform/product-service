package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.service.CategoryService;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.ProductCategoryMapping;
import com.smplatform.product_service.domain.product.repository.ProductCategoryMappingRepository;
import com.smplatform.product_service.domain.product.service.ProductCategoryMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryMappingServiceImpl implements ProductCategoryMappingService {
    private final ProductCategoryMappingRepository productCategoryMappingRepository;
    private final CategoryService categoryService;

    @Override
    public void save(int categoryId, long productId) {
        List<Category> categories = categoryService.getCategoryListWithAllParent(categoryId);
        List<ProductCategoryMapping> productCategoryMappings = categories.stream()
                .map(c-> new ProductCategoryMapping(c.getCategoryId(), productId))
                .collect(Collectors.toList());
        productCategoryMappingRepository.saveAll(productCategoryMappings);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponseDto.GetProduct> getProductsByCategoryId(int categoryId) {
        List<Product> products = productCategoryMappingRepository.findAllByCategoryId(categoryId);
        return products.stream()
                .map(p->ProductResponseDto.GetProduct.of(p))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByCategoryId(int categoryId) {

    }

    @Override
    public void deleteByProductId(long productId) {

    }
}
