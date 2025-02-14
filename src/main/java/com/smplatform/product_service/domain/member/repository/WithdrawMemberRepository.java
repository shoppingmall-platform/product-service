package com.smplatform.product_service.domain.member.repository;

import com.smplatform.product_service.domain.member.entity.WithdrawMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawMemberRepository extends JpaRepository<WithdrawMember, String> {
}
