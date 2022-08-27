package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findProductByName(String name);
}
