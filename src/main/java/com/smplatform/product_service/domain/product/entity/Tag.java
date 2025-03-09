package com.smplatform.product_service.domain.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_name")
    private long tagName;

    @JoinColumn(name= "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
