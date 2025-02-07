package com.smplatform.product_service.domain.member.dto;

import com.smplatform.product_service.domain.member.enums.Gender;
import com.smplatform.product_service.domain.member.enums.MemberLevel;
import com.smplatform.product_service.domain.member.enums.search.DateSerach;
import com.smplatform.product_service.domain.member.enums.search.OrderDateSearch;
import com.smplatform.product_service.domain.member.enums.search.OrderSerach;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MemberSearchRequestParamDto {
    private String name;
    private String email;
    private MemberLevel level;
    private DateSerach dateSearch; // 가입일, 생일
    private LocalDate startDate;
    private LocalDate endDate;
    private int startAge;
    private int endAge;
    private Gender gender;
    private OrderSerach orderSerach; // 총 주문금액, 총 실결제금액. 총 주문건수, 총 실주문건수
    private int orderSearchStartValue;
    private int orderSearchEndValue;
    private OrderDateSearch orderDateSearch;
    private LocalDate startOrderDate;
    private LocalDate endOrderDate;
    private Long productId;
}
