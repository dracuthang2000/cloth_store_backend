package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long id);
}
