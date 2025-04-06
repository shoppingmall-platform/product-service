package com.smplatform.product_service.domain.product.service;

import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseDto.GetProduct getProduct(long productId);

    String saveProduct(ProductRequestDto.SaveProduct productDto);

    String updateProduct(ProductRequestDto.UpdateProduct productDto);

    List<ProductResponseDto.GetProduct> getProducts(Pageable pageable);

    List<ProductResponseDto.GetProductForUsers> getProductsForUsers(int categoryId,
                                                                    ProductRequestDto.ProductSearchCondition condition,
                                                                    Pageable pageable);

    List<ProductResponseDto.GetTag> getProductsTags();
}
