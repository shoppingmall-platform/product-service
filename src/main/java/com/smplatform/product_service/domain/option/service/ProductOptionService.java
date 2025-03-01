package com.smplatform.product_service.domain.option.service;

import com.smplatform.product_service.domain.option.dto.ProductOptionRequestDto;

public interface ProductOptionService {
    int saveProductoption(ProductOptionRequestDto.SaveProductOption productOptionRequestDto);
}
