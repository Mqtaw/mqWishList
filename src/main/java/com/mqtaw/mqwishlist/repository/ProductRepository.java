package com.mqtaw.mqwishlist.repository;

import com.mqtaw.mqwishlist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
