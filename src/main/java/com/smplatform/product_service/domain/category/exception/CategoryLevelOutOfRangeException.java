package com.smplatform.product_service.domain.category.exception;

import org.springframework.http.HttpStatus;

public class CategoryLevelOutOfRangeException extends AbstractCategoryException {
    public CategoryLevelOutOfRangeException(Integer level) {
        super("level 범위 : 1~3 (1=대, 2=중, 3=소) | 요청 level 값 : " + level);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
