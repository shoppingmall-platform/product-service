package com.smplatform.product_service.domain.option.dto;

import com.smplatform.product_service.domain.option.entity.OptionType;
import com.smplatform.product_service.domain.option.entity.OptionValue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class OptionRequestDto {
    private OptionRequestDto() {
    }

    @Getter
    @Setter
    public static class SaveOption {
        private String optionTypeName;
        private List<OptionRequestDto.SaveOptionValue> optionValues;
        private LocalDateTime createdAt;

        public OptionType toEntity() {
            return OptionType.builder()
                    .optionTypeName(optionTypeName)
                    .createdAt(createdAt)
                    .build();
        }
    }

    @Getter
    public static class SaveOptionValue {
        private String optionValueName;

        public OptionValue toEntity() {
            return OptionValue.builder()
                    .optionValueName(optionValueName)
                    .build();
        }
    }
}
