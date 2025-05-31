package com.smplatform.product_service.domain.member.repository;

import com.smplatform.product_service.domain.member.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByMemberMemberId(String memberId);

    Optional<Address> findByMember_MemberIdAndIsDefault(String memberId, Boolean isDefault);
}
