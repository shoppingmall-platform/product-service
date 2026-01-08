package com.smplatform.product_service.domain.order.dto;

import com.smplatform.product_service.domain.order.entity.OrderProductStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderSearchRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberOrdersSearch {
        private SearchConditionsDto conditions;
        private PageableDto pageable;
    }

    @Getter
    @AllArgsConstructor
    public static class SearchConditionsDto {
        private OrderSearchType type = OrderSearchType.ALL;
        private OrderProductStatus status = null;
        private LocalDate startDate = LocalDate.now().minusWeeks(1);
        private LocalDate endDate = LocalDate.now();
    }

    public enum OrderSearchType {
        ALL(null),
        NORMAL(OrderProductStatus.StatusType.NORMAL),
        SHIPPING(OrderProductStatus.StatusType.SHIPPING),
        CANCEL_RETURN_EXCHANGE(OrderProductStatus.StatusType.CANCEL_RETURN_EXCHANGE);

        private final OrderProductStatus.StatusType type;

        OrderSearchType(OrderProductStatus.StatusType type) {
            this.type = type;
        }

        public EnumSet<OrderProductStatus> getTargets() {
            return type==null? EnumSet.noneOf(OrderProductStatus.class)
                    : OrderProductStatus.ofType(type);
        }
    }

    @Getter
    public static class PageableDto {
        @Min(value = 0, message = "page 값은 0 이상이어야 합니다")
        private int page = 0;
        @Min(value = 1)
        @Max(value = 100)
        private int size = 10;
        private SortDto sort;

        public Sort.Direction getDirection() {
            return Optional.ofNullable(sort)
                    .map(SortDto::getDirection)
                    .flatMap(Sort.Direction::fromOptionalString)
                    .orElse(Sort.Direction.DESC);
        }
        public List<Sort.Order> getOrders() {
            return Optional.ofNullable(sort)
                    .map(SortDto::getProperties)
                    .orElse(List.of("orderDate"))
                    .stream().map(prop -> new Sort.Order(getDirection(), prop))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class SortDto {
        private String direction;
        private List<String> properties;
    }
}