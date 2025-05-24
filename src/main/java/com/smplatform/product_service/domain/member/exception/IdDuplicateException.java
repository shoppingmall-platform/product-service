package com.smplatform.product_service.domain.member.exception;

import org.springframework.http.HttpStatus;

public class IdDuplicateException extends AbstractMemberException {
    public IdDuplicateException(String id) {
        super("중복된 아이디 오류. id :" + id);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
