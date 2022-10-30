package com.mqtaw.mqwishlist.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "hashed_password", nullable = false)
    private String password;

    @NotBlank(message = "Name is mandatory")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
