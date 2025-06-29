package com.smplatform.product_service.domain.order.entity;

import com.smplatform.product_service.domain.discount.entity.Discount;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    private Integer quantity;
    @Column(name = "order_price")
    private Long orderPrice;
    @Column(name = "order_product_status")
    private String orderProductStatus;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_option_id")
    private Long productOptionId;
    @Column(name = "product_option_name")
    private String productOptionName;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "disocunt_id")
    private Long discountId;
    @Column(name = "disocunt_type")
    private Discount.Type discountType;
    @Column(name = "disocunt_value")
    private Integer discountValue;
}
