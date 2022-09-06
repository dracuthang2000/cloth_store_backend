package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.dto.filter.ConditionSearchListProduct;
import com.ptithcm.clothing_store.model.dto.filter.FilterProduct;
import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findProductIsNew();
    Product findByTag(String tag);
    List<Product> getTopFiveBestSellerProduct();
    String save(Product product);
    List<Product> findContainByName(String keyword);
    List<Product> findProductByContainTagMaterialAndTagLabelAndTagBrand(FilterProduct filter);
    List<Product> findProductByNewAndName(ConditionSearchListProduct condition);
}
