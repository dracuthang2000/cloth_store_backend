package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long id);
    public List<Product> findProductIsNew();
    public Product findByTag(String tag);
}
