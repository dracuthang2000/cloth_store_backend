package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.ProductColorSize;

import java.util.List;
import java.util.Optional;

public interface ProductColorSizeService {
    List<ProductColorSize> findAllById(List<Long> ids);
    ProductColorSize findById(Long id);
    String save(ProductColorSize productColorSize);
    Integer getQuantityInStock(Long id);
}
