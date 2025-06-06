package com.smplatform.product_service.domain.category.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class CategoryNotFoundException extends AbstractCategoryException {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String msg) {
        super(msg);
    }

    public CategoryNotFoundException(int id) {
        super("존재하지 않는 카테고리 ID 입니다 : " + id);
        log.error("존재하지 않는 카테고리 ID 입니다 : " + id);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
