package com.smplatform.product_service.domain.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.order.entity.OrderProduct;
import com.smplatform.product_service.domain.order.entity.OrderProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class OrderSearchResponseDto {

    @Getter
    @AllArgsConstructor
    public static class MemberOrdersGet {
        private List<MemberOrder> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
    }

    @Getter
    public static class MemberOrder {
        private long orderId;
        private LocalDateTime orderDate;
        private String mainProductName;
        private int productCount;

        @Setter
        private List<OrderProductDto> products;

        @QueryProjection
        public MemberOrder(long orderId, LocalDateTime orderDate, String productName, int productCount) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.mainProductName = productName;
            this.productCount = productCount;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class OrderProductDto {
        private Long productId;
        private String productName;
        private String optionName;
        private int quantity;
        private long price;
        private long discountId;
        private Discount.Type discountType;
        private OrderProductStatus orderProductStatus;

        public static OrderProductDto of(OrderProduct orderProduct) {
            return new OrderProductDto(
                    orderProduct.getProductId(),
                    orderProduct.getProductName(),
                    orderProduct.getProductOptionName(),
                    orderProduct.getQuantity(),
                    orderProduct.getOrderPrice(),
                    orderProduct.getDiscountId(),
                    orderProduct.getDiscountType(),
                    orderProduct.getOrderProductStatus()
            );
        }
    }
}