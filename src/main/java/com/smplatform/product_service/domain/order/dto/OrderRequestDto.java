package com.smplatform.product_service.domain.order.dto;

import lombok.Getter;

import java.util.List;

public class OrderRequestDto {
    @Getter
    public static class OrderSave {
        private OrderRequestDto.OrderAddress addressInfo;
        private List<OrderRequestDto.OrderItem> items;
        private String paymentMethod;
        private OrderRequestDto.OrderDiscount orderDiscount;
        private OrderRequestDto.OrderDetail orderDetail;
    }

    @Getter
    public static class OrderAddress {
        private String address1;
        private String address2;
        private Integer postalCode;
        private String receiver;
        private String phoneNumber;
        private String email;
    }

    @Getter
    public static class OrderItem {
        private Long cartItemId;
        private Long productId;
        private Long productOptionId;
        private int quantity;
    }

    @Getter
    public static class OrderDiscount {
        private Long couponId;
        private Integer points;
    }

    @Getter
    public static class OrderDetail {
        private int originalTotal;
        private int discountedTotal;
        private int productDisocunt;
        private int additionalDiscount;
        private int shippingFee;
        private int finalAmount;
    }

}
