package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.Wish;

import java.util.List;

public interface WishService {

    public List<Wish> findAllWishes();

    public Wish findWishById(int id);

    public void save(Wish wish);

    public void deleteById(int id);
}
