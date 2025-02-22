package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.product.domain.Product;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
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
    public ProductResponseDto.GetProduct getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(String.format("product { %d } not found", productId));
        }
        Product productEntity = product.get();

        return ProductResponseDto.GetProduct.of(productEntity);
    }

    @Override
    public String saveProduct(ProductRequestDto.SaveProduct productDto) {
        Product product = productDto.toEntity();
        product.setCategory(
                categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("category not found"))
        );
        if (Objects.nonNull(productDto.getDiscountId())) {
            product.setDiscount(
                    discountRepository.findById(productDto.getDiscountId()).orElse(null)
            );
        }
        productRepository.save(product);
        return String.valueOf(product.getId());
    }

    @Override
    public String updateProduct(ProductRequestDto.UpdateProduct productDto) {
        // product, category, discount 찾고
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("product id : %d not found", productDto.getId())));

        product.setCategory(
                categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException(String.format("category id : %d not found", productDto.getCategoryId())))
        );

        if (!Objects.isNull(productDto.getDiscountId())) {
            product.setDiscount(
                    discountRepository.findById(productDto.getDiscountId())
                            .orElseThrow(() -> new RuntimeException(String.format("discount id : %d not found", productDto.getDiscountId())))
            );
        }

        // 각 필드 업데이트 후
        BeanUtils.copyProperties(productDto, product, "id");

        // 저장
        return String.valueOf(productRepository.save(product).getId());
    }
}
