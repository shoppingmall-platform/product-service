package com.smplatform.product_service.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product_images")
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long productImageId;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private String path;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ProductImageStatus status = ProductImageStatus.ACTIVE;

    public enum ProductImageStatus {
        ACTIVE, DELETED
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public ProductImages(Product product, String path) {
        this.product = product;
        this.path = path;
    }

    public void update(String path) {
        this.path = path;
    }

}
