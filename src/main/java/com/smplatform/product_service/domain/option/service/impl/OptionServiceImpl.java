package com.smplatform.product_service.domain.option.service.impl;

import com.smplatform.product_service.domain.option.dto.OptionRequestDto;
import com.smplatform.product_service.domain.option.dto.OptionResponseDto;
import com.smplatform.product_service.domain.option.entity.OptionType;
import com.smplatform.product_service.domain.option.entity.OptionValue;
import com.smplatform.product_service.domain.option.exception.OptionTypeNotFoundException;
import com.smplatform.product_service.domain.option.repository.OptionTypeRepository;
import com.smplatform.product_service.domain.option.repository.OptionValueRepository;
import com.smplatform.product_service.domain.option.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionValueRepository optionValueRepository;
    private final OptionTypeRepository optionTypeRepository;

    @Override
    @Transactional
    public long saveOption(OptionRequestDto.SaveOption optionRequestDto) {
        optionRequestDto.setCreatedAt(LocalDateTime.now());
        OptionType optionType = optionRequestDto.toEntity();
        optionType = optionTypeRepository.save(optionType);
        List<OptionRequestDto.SaveOptionValue> optionValueDtos = optionRequestDto.getOptionValues();
        OptionType finalOptionType = optionType;

        List<OptionValue> optionValues = optionValueDtos.stream().map(dto -> {
            OptionValue optionValue = dto.toEntity();
            optionValue.setOptionType(finalOptionType);
            return optionValue;
        }).toList();
        optionValueRepository.saveAll(optionValues);

        return optionType.getOptionTypeId();
    }

    @Override
    public List<OptionResponseDto.GetOption> getOptions(Pageable pageable) {
        Page<OptionType> optionTypes = optionTypeRepository.findAll(pageable);
        return optionTypes.stream()
                .map(optionType -> {
                    List<OptionResponseDto.GetOptionValue> optionValueDtos = optionType.getOptionValues().stream()
                            .map(OptionResponseDto.GetOptionValue::of)
                            .toList();
                    OptionResponseDto.GetOption optionDto = OptionResponseDto.GetOption.of(optionType);
                    optionDto.setOptionValues(optionValueDtos);
                    return optionDto;
                }).toList();
    }

    @Override
    public void deleteOptionType(long id) {
        optionTypeRepository.delete(optionTypeRepository.findById(id).orElseThrow(OptionTypeNotFoundException::new));
    }
}
