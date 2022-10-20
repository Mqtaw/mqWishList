package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.Product;
import com.mqtaw.mqwishlist.entity.WishList;
import com.mqtaw.mqwishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService{

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public List<WishList> findAllWishLists() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList findWishListById(int id) {
        Optional<WishList> optional = wishListRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void save(WishList wishList) {
        wishListRepository.save(wishList);
    }

    @Override
    public void deleteById(int id) {
        wishListRepository.deleteById(id);
    }
}
