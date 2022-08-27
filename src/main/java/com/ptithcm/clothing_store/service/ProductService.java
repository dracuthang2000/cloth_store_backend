package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findProductIsNew();
    Product findByTag(String tag);
    String save(Product product);
}
