package com.mqtaw.mqwishlist.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="wishlists")
@Getter
@Setter
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "wishList", fetch = FetchType.EAGER)
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void addProductToWishList(Product product) {
        products.add(product);
        product.setWishList(this);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
