package com.mqtaw.mqwishlist.repository;

import com.mqtaw.mqwishlist.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Integer> {
}
