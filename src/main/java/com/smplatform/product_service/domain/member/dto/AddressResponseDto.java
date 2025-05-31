package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.entity.Address;
import lombok.Builder;
import lombok.Getter;

public class AddressResponseDto {

    @Getter
    @Builder
    public static class AddressGet {
        private Long addressId;
        private String alias;
        private String zipcode;
        private String address1;
        private String address2;
        private String receiverName;
        private String phoneNumber;
        private int isDefault;

        public static AddressResponseDto.AddressGet of(Address address) {
            return AddressGet.builder()
                    .addressId(address.getAddressId())
                    .alias(address.getAlias())
                    .zipcode(address.getZipcode())
                    .address1(address.getDefaultAddress())
                    .address2(address.getDetailAddress())
                    .receiverName(address.getReceiverName())
                    .phoneNumber(address.getPhoneNumber())
                    .isDefault(address.getIsDefault() ? 1 : 0)
                    .build();
        }
    }
}
