package com.smplatform.product_service.domain.order.service.impl;

import com.smplatform.product_service.domain.order.dto.PaymentRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    private PaymentServiceImpl paymentService;

//    @Test
//    void 실결제_승인API_호출_성공() {
//        // 🔐 실제 결제 완료 후 리디렉션에서 얻은 값을 아래에 대입하세요
//        PaymentRequestDto.PaymentConfirm dto = new PaymentRequestDto.PaymentConfirm();
//        dto.setPaymentKey("pay_abc123"); // 실제 결제 완료 후 받은 키
//        dto.setOrderId("order_123456");  // 결제 시 사용한 주문 ID
//        dto.setAmount(10000L);           // 결제 금액 (일치해야 함)
//
//        String result = paymentService.confirmPayment(dto);
//        System.out.println("응답 결과: " + result);
//
//        assertTrue(result.contains("DONE")); // 성공 응답 시 DONE 포함
//    }

}