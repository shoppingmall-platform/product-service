package com.smplatform.product_service.domain.member.service;

import com.smplatform.product_service.domain.member.dto.*;

import java.util.List;

public interface MemberService {

    /**
     * Create
     */
    String createMember(MemberCreateDto newMemberDto);

    /**
     * Read
     */
    MemberResponseDto getMember(String id);
    List<MemberResponseDto> getMembers();
    List<MemberResponseDto> searchMembers(MemberSearchRequestParamDto searchRequestParamDto);

    /**
     * Update
     */
    String updateMember(String id, MemberUpdateDto updateMemberDto);

    /**
     * Delete
     */
    Void deleteMember(String id, String memo);

    /**
     * Actions
     */
    MemberCredentialDto getMemberCredential(String id);
}
