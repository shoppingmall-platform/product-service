package com.smplatform.product_service.domain.option.service.impl;

import com.smplatform.product_service.domain.option.dto.ProductOptionRequestDto;
import com.smplatform.product_service.domain.option.entity.OptionValue;
import com.smplatform.product_service.domain.option.entity.ProductOption;
import com.smplatform.product_service.domain.option.repository.OptionValueRepository;
import com.smplatform.product_service.domain.option.repository.ProductOptionRepository;
import com.smplatform.product_service.domain.option.service.ProductOptionService;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.option.exception.OptionValueNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final OptionValueRepository optionValueRepository;

    @Override
    @Transactional
    public int saveProductoption(ProductOptionRequestDto.SaveProductOption productOptionRequestDto) {
        Product product = productRepository
                .findById(productOptionRequestDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("product %d not found", productOptionRequestDto.getProductId())));

        OptionValue optionValue = optionValueRepository
                .findById(productOptionRequestDto.getOptionValueId())
                .orElseThrow(() -> new OptionValueNotFoundException(String.format("OptionValue id : %d not found", productOptionRequestDto.getOptionValueId())));

        ProductOption productOption = productOptionRequestDto.toEntity();
        productOption.setProduct(product);
        productOption.setOptionValue(optionValue);

        return productOptionRepository.save(null).getProductOptionId();
    }
}
