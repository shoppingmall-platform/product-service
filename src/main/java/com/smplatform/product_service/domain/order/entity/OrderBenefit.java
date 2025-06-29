package com.smplatform.product_service.domain.order.entity;

import com.smplatform.product_service.domain.coupon.entity.MemberCoupon;
import jakarta.persistence.*;

@Entity
@Table(name = "order_benefits")
public class OrderBenefit {
    @Id
    private Long orderId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "point_discount")
    private Integer pointDiscount;
    @Column(name = "coupon_discount")
    private Integer couponDiscount;
    @OneToOne
    @JoinColumn(name = "member_coupon_id")
    private MemberCoupon coupon;
    @Column(name = "total_benefit")
    private Long totalBenefit;
}
