package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.entity.ProductImages;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductImageRepository;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.dto.ProductImageResponseDto;
import com.smplatform.product_service.domain.product.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponseDto.ProductImageInfo> getProductProductImageList(Long productId) {
        List<ProductImages> productImages;
        if (productId == null) {
            productImages = productImageRepository.findAll();
        } else {
            productImages = productImageRepository.findAllByProduct_Id(productId);
        }
        return productImages.stream()
                .map(ProductImageResponseDto.ProductImageInfo::of)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProductImages(long productId, List<String> paths) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("존재하지 않는 상품 Id 입니다 : " + productId));
        List<ProductImages> productImages = paths.stream()
                .map(path -> ProductImages.builder()
                        .path(path)
                        .product(product)
                        .build())
                .collect(Collectors.toList());
        productImageRepository.saveAll(productImages);
    }

    @Override
    public void deleteProductImage(List<Long> ProductImageIds) {
        productImageRepository.deleteAllById(ProductImageIds);
    }
}
