package com.smplatform.product_service.domain.cart.entity;

import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.product.entity.ProductOption;
import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;
}
