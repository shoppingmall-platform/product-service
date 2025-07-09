package com.smplatform.product_service.domain.order.entity;

import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.member.entity.Delivery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_products")
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery deliveryId;

    private Integer quantity;
    @Column(name = "order_price")
    private Integer orderPrice;
    @Column(name = "order_product_status")
    private OrderProductStatus orderProductStatus;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_option_id")
    private Long productOptionId;
    @Column(name = "product_option_name")
    private String productOptionName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "disocunt_id")
    private Integer discountId;
    @Column(name = "disocunt_type")
    private Discount.Type discountType;
    @Column(name = "disocunt_value")
    private Integer discountValue;
}
