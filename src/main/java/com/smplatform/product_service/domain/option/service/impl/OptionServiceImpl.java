package com.smplatform.product_service.domain.option.service.impl;

import com.smplatform.product_service.domain.option.repository.OptionTypeRepository;
import com.smplatform.product_service.domain.option.repository.OptionValueRepository;
import com.smplatform.product_service.domain.option.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionValueRepository optionValueRepository;
    private final OptionTypeRepository optionTypeRepository;

}
