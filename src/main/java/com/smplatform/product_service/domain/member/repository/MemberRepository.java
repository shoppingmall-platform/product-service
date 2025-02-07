package com.smplatform.product_service.domain.member.repository;

import com.smplatform.product_service.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, CustomMemberRepository {
    Optional<Member> findById(String id);
}