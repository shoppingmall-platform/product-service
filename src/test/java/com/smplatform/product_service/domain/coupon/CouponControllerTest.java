package com.smplatform.product_service.domain.coupon;

import com.smplatform.product_service.domain.coupon.controller.CouponController;
import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class CouponControllerTest {

    @Autowired
    private CouponController couponController;

    private CouponRequestDto couponRequestDto;
    private CouponResponseDto couponResponseDto;

    @BeforeEach
    void setUp() {
        CouponRequestDto.CouponCreate couponCreateDto = new CouponRequestDto.CouponCreate();
        ReflectionTestUtils.setField(couponCreateDto, "couponName", "testCoupon");
    }

}