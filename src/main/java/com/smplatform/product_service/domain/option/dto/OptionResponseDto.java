package com.smplatform.product_service.domain.option.dto;

import com.smplatform.product_service.domain.option.entity.OptionType;
import com.smplatform.product_service.domain.option.entity.OptionValue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class OptionResponseDto {

    @AllArgsConstructor
    @Builder
    @Getter
    @ToString
    public static class GetOption {
        private long optionTypeId;
        private String optionTypeName;
        @Setter
        private List<OptionResponseDto.GetOptionValue> optionValues;
        private LocalDateTime createdAt;

        public static OptionResponseDto.GetOption of(OptionType optionType) {
            return GetOption.builder()
                    .optionTypeId(optionType.getOptionTypeId())
                    .optionTypeName(optionType.getOptionTypeName())
                    .createdAt(optionType.getCreatedAt())
                    .build();
        }
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class GetOptionValue {
        private long optionValueId;
        private String optionValueName;

        public static OptionResponseDto.GetOptionValue of(OptionValue optionValue) {
            return GetOptionValue.builder()
                    .optionValueId(optionValue.getOptionValueId())
                    .optionValueName(optionValue.getOptionValueName())
                    .build();
        }
    }
}
