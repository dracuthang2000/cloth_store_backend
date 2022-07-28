package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.ProductColorSize;

import java.util.List;
import java.util.Optional;

public interface ProductColorSizeService {
    public List<ProductColorSize> findAllById(List<Long> ids);
    public ProductColorSize findById(Long id);
}
