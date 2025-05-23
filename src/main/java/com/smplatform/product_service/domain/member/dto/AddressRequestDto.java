package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.entity.Address;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class AddressRequestDto {
    @Getter
    public static class AddressSave {
        @NotBlank(message = "별칭은 필수 입니다.")
        private String alias;

        @Pattern(regexp = "\\d{5}", message = "우편번호는 5자리 숫자여야 합니다.")
        private String zipcode;

        @NotBlank(message = "기본주소는 필수입니다.")
        private String address1;

        private String address2;

        @NotBlank(message = "수령인 이름은 필수입니다.")
        private String receiverName;

        @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
        private String phoneNumber;

        @Min(value = 0, message = "기본 주소 여부는 0 또는 1 이어야 합니다.")
        @Max(value = 1, message = "기본 주소 여부는 0 또는 1 이어야 합니다.")
        private int isDefault;

        public Address toEntity() {
            return Address.builder()
                    .alias(this.alias)
                    .zipcode(this.zipcode)
                    .defaultAddress(this.address1)
                    .detailAddress(this.address2)
                    .receiverName(this.receiverName)
                    .phoneNumber(this.phoneNumber)
                    .isDefault(this.isDefault != 0)
                    .build();
        }
    }

    @Getter
    public static class AddressUpdate {
        private Long addressId;

        @NotBlank(message = "별칭은 필수 입니다.")
        private String alias;

        @Pattern(regexp = "\\d{5}", message = "우편번호는 5자리 숫자여야 합니다.")
        private String zipcode;

        @NotBlank(message = "기본주소는 필수입니다.")
        private String address1;

        private String address2;

        @NotBlank(message = "수령인 이름은 필수입니다.")
        private String receiverName;

        @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
        private String phoneNumber;

        @Min(value = 0, message = "기본 주소 여부는 0 또는 1 이어야 합니다.")
        @Max(value = 1, message = "기본 주소 여부는 0 또는 1 이어야 합니다.")
        private Integer isDefault;
    }

    @Getter
    public static class AddressDelete {
        private Long addressId;
    }
}
