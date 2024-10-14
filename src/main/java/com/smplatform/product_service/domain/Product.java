package com.smplatform.product_service.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
