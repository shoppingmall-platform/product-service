package com.smplatform.product_service.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product_thumpnail")
public class Thumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id")
    private long thumbnailId;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private String path;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Thumbnail(long thumbnailId, Product product, String path) {
        this.thumbnailId = thumbnailId;
        this.product = product;
        this.path = path;
    }

    public void update(String photoUrl) {
        this.path = path;
    }

}
