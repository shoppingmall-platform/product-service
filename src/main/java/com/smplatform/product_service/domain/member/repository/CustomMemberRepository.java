package com.smplatform.product_service.domain.member.repository;

import com.smplatform.product_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.product_service.domain.member.entity.Member;

import java.util.List;

public interface CustomMemberRepository {
    List<Member> searchMember(final MemberSearchRequestParamDto searchRequestParamDto);
}
