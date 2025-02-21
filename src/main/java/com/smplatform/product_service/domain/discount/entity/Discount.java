package com.smplatform.product_service.domain.discount.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;
    @Column(name = "discount_name")
    private String discountName;
    @Column(name = "discount_type")
    @Enumerated(EnumType.STRING)
    private Type discountType;
    @Column(name = "discount_value")
    private int discountValue;
    @Column(name = "discount_start_date")
    private LocalDateTime discountStartDate;
    @Column(name = "discount_end_date")
    private LocalDateTime discountEndDate;

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        RATE("할인율"),
        AMOUNT("할인금액");

        private final String description;

        public static Type fromDescription(String description) {
            return Arrays.stream(Type.values())
                    .filter(type -> type.description.equals(description))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid discount type: " + description));
        }
    }

}

