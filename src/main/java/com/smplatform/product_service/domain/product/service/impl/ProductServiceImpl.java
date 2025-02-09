package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.product.domain.Product;
import com.smplatform.product_service.domain.product.dto.ProductDto;
import com.smplatform.product_service.exception.DiscountNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new DiscountNotFoundException();
        }
        Product productEntity = product.get();

        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .build();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        Product productEntity = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.isDeleted(),
                productDto.getCategory(),
                productDto.getProductState(),
                productDto.isSelling(),
                productDto.getCreatedAt(),
                productDto.getPrice(),
                productDto.getDiscount(),
                productDto.getSummaryDescription(),
                productDto.getSimpleDescription()
        );
        productRepository.save(productEntity);
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .build();
    }
}
