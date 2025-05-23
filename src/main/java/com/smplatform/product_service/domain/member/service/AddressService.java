package com.smplatform.product_service.domain.member.service;

import com.smplatform.product_service.domain.member.dto.AddressRequestDto;
import com.smplatform.product_service.domain.member.dto.AddressResponseDto;

import java.util.List;

public interface AddressService {
    Long saveAddress(String userId, AddressRequestDto.AddressSave requestDto);

    List<AddressResponseDto.AddressGet> getAddresses(String memberId);

    Long updateAddress(String memberId, AddressRequestDto.AddressUpdate requestDto);

    void deleteAddress(String memberId, AddressRequestDto.AddressDelete requestDto);
}
