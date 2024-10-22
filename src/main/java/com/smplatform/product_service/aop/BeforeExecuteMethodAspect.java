package com.smplatform.product_service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeExecuteMethodAspect {
    @Before("@annotation(com.smplatform.product_service.annotation.AdminOnly)")
    public void hasAdminRole() {

    }
}
