package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    /**
     * 단일 제품 조회
     *
     * @param productId
     * @return
     */
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
        BeanUtils.copyProperties(productDto, product, "id");

        return String.valueOf(productRepository.save(product).getId());
    }

    /**
     * 전체 제품을 조회
     *
     * @param pageable
     * @return
     */
    @Override
    public List<ProductResponseDto.GetProduct> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponseDto.GetProduct> resultProducts = new ArrayList<>();
        // N+1 entitygraph 사용
        for (Product product : products) {
            resultProducts.add(ProductResponseDto.GetProduct.of(product));
        }
        return resultProducts;
    }
}
