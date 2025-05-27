package com.smplatform.product_service.domain.cart.entity;

import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartItems")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    @Column
    private int quantity;

    private CartItem(Member member, ProductOption productOption) {
        this.member = member;
        this.productOption = productOption;
    }

    public static CartItem createCartItem(Member member, ProductOption productOption) {
        return new CartItem(member, productOption);
    }

    public void updateProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }
}
