package com.mqtaw.mqwishlist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private List<Wish> wishes;

    public List<Wish> getWishes() {
        return wishes;
    }

    public void addWishToWishList(Wish wish) {
        wishes.add(wish);
        wish.setWishList(this);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
