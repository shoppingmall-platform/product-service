package com.smplatform.product_service.advice;

import com.smplatform.product_service.domain.category.exception.AbstractCategoryException;
import com.smplatform.product_service.domain.member.exception.AbstractMemberException;
import com.smplatform.product_service.domain.product.exception.ThumbnailNotFoundException;
import com.smplatform.product_service.exception.DiscountNotFoundException;
import com.smplatform.product_service.exception.ProductStateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class ProductControllerAdvice {

    // 도메인별 예외처리

    @ExceptionHandler(ProductStateNotFoundException.class)
    public ResponseEntity<String> handleProductStateNotFoundException(DiscountNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Category 관련 에러 핸들러
     *
     * @param e AbstractCategoryException
     * @return
     */
    @ExceptionHandler(AbstractCategoryException.class)
    public ResponseEntity<String> handleAbstractCategoryException(AbstractCategoryException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(ThumbnailNotFoundException.class)
    public ResponseEntity<String> handleThumbnailNotFoundException(ThumbnailNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AbstractMemberException.class)
    public ResponseEntity<String> handleAbstractMemberException(AbstractMemberException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    /**
     * // 도메인 이외의 서버 예외처리
     */


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException e) {
        List<String> errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
