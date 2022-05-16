package com.example.projectb2c.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rate")
    private int rate;
    @Column(name = "reviewtext")
    private String reviewtext;
    @Column(name = "namecus")
    private String namecus;
    @Column(name = "emailcus")
    private String emailcus;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
}
