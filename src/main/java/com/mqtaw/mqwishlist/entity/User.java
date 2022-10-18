package com.mqtaw.mqwishlist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hashed_password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    @OneToMany(mappedBy = "owner")
    private Set<WishList> wishLists;

    public Set<WishList> getWishLists() {
        return wishLists;
    }

    public void addWishListToUser(WishList wishList) {
        wishLists.add(wishList);
        wishList.setOwner(this);
    }

    public User() {};

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }
}
