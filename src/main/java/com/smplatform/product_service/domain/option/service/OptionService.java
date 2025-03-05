package com.smplatform.product_service.domain.option.service;

import com.smplatform.product_service.domain.option.dto.OptionRequestDto;
import com.smplatform.product_service.domain.option.dto.OptionResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionService {

    @Transactional
    long saveOption(OptionRequestDto.SaveOption optionRequestDto);

    List<OptionResponseDto.GetOption> getOptions(Pageable pageable);
}
