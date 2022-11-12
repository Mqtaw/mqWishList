package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.Wish;
import com.mqtaw.mqwishlist.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    private WishRepository wishRepository;

    @Override
    public List<Wish> findAllWishes() {
        return wishRepository.findAll();
    }

    @Override
    public Wish findWishById(int id) {
        Optional<Wish> optional = wishRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void save(Wish wish) {
        wishRepository.save(wish);
    }

    @Override
    public void deleteById(int id) {
        System.out.println("delete wish" + id);
        wishRepository.deleteById(id);
    }
}
