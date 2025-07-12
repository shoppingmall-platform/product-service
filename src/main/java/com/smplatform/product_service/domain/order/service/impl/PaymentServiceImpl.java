package com.smplatform.product_service.domain.order.service.impl;

import com.smplatform.product_service.domain.order.dto.PaymentRequestDto;
import com.smplatform.product_service.domain.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;

    @Value("${toss.api.secret}")
    private String secret;

    @Override
    public String confirmPayment(PaymentRequestDto.PaymentConfirm dto) {
        String encodedAuth = Base64.getEncoder().encodeToString((secret + ":").getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(encodedAuth);

        HttpEntity<PaymentRequestDto.PaymentConfirm> entity = new HttpEntity<>(dto, headers);

        String url = "https://api.tosspayments.com/v1/payments/confirm";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
