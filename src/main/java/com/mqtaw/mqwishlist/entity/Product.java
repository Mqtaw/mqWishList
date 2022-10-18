package com.mqtaw.mqwishlist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "name")
    private String link;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_locked")
    private boolean isLocked;

    @ManyToOne
    @JoinColumn(name="wishlist_id", nullable = false)
    private WishList wishList;

}
