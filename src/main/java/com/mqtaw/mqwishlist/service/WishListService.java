package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.WishList;

import java.util.List;

public interface WishListService {

    public List<WishList> findAllWishLists();

    public WishList findWishListById(int id);

    public void save(WishList wishList);

    public void deleteById(int id);
}
