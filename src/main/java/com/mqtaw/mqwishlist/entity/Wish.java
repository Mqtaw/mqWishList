package com.mqtaw.mqwishlist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="wishes")
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_locked", nullable = false, columnDefinition = "boolean default false")
    private boolean isLocked;

    @ManyToOne
    @JoinColumn(name="wishlist_id", nullable = false)
    private WishList wishList;

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        this.isLocked = locked;
    }


    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", comment='" + comment + '\'' +
                ", isLocked=" + isLocked +
                '}';
    }
}
