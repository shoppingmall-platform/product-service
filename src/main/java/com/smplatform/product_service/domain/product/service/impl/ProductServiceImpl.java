package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.entity.Category;
import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.product.domain.Product;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    @Override
    public ProductRequestDto getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(String.format("product { %d } not found", productId));
        }
        Product productEntity = product.get();

        return ProductRequestDto.of(productEntity);
    }

    @Override
    public String saveProduct(ProductRequestDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("category not found"));

        productDto.setCategory(category);
        Product product = productDto.toEntity();
        productRepository.save(product);

        return String.valueOf(product.getId());
    }

    @Override
    public String updateProduct(ProductRequestDto productRequestDto) {
        // product, category, discount 찾고
        Product product = productRepository.findById(productRequestDto.getId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("product id : %d not found", productRequestDto.getId())));

        Category category = categoryRepository.findById(productRequestDto.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException(String.format("category id : %d not found", productRequestDto.getId())));
        product.setCategory(category);

        if (!Objects.isNull(productRequestDto.getDiscount())) {
            Discount discount = discountRepository.findById(productRequestDto.getDiscount().getDiscountId())
                    .orElseThrow(() -> new RuntimeException(String.format("discount id : %d not found", productRequestDto.getDiscount().getDiscountId())));
            product.setDiscount(discount);
        }

        // 각 필드 업데이트 후
        BeanUtils.copyProperties(productRequestDto, product, "id", "category", "discount");

        // 저장
        return String.valueOf(productRepository.save(product).getId());
    }
}
