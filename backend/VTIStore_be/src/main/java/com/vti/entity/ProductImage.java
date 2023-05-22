package com.vti.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;

}
