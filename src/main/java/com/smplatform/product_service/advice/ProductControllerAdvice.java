package com.smplatform.product_service.advice;

import com.smplatform.product_service.exception.DiscountNotFoundException;
import com.smplatform.product_service.exception.ProductStateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductStateNotFoundException.class)
    public ResponseEntity<String> handleProductStateNotFoundException(DiscountNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
