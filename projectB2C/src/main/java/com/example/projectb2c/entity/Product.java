package com.example.projectb2c.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName")
    private String productName;
    @Column(name = "detail")
    private String detail;
    @Column(name = "price")
    private int price;
    @Column(name = "branch")
    private String branch;
    @Column(name = "yearofmanufacture")
    private String yearofmanufacture;
    @Column(name = "videosrc")
    private String videosrc;
    @Column(name = "img1")
    private String img1;
    @Column(name = "img2")
    private String img2;
    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Category category;
    @OneToMany(mappedBy="product")
    private List<Review> reviewList;
}
