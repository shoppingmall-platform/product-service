package com.smplatform.product_service.domain.member.entity;

import com.smplatform.product_service.domain.order.entity.Order;
import jakarta.persistence.*;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String deliveryCode;

    private String deliveryCompany;

    private Integer deliveryFee;

    private Integer postalCode;

    private String address1;
    private String address2;
    private String receiverName;
    private String phoneNumber;
}
