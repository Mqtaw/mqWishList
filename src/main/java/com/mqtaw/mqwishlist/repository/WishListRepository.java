package com.mqtaw.mqwishlist.repository;

import com.mqtaw.mqwishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
}
