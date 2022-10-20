package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAllProducts();

    public Product findProductById(int id);

    public void save(Product product);

    public void deleteById(int id);
}
