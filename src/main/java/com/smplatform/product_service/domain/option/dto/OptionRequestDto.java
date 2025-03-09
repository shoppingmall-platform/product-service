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
    public static class SaveOption {
        private String optionTypeName;
        private List<OptionRequestDto.SaveOptionValue> optionValues;

        public OptionType toEntity() {
            return OptionType.builder()
                    .optionTypeName(optionTypeName)
                    .createdAt(LocalDateTime.now())
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

    @Getter
    public static class DeleteOption {
        private long optionTypeId;
    }

}
