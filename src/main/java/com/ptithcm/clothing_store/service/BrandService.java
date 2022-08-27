package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> findAll();
    Brand findOne(Long id);
    String save(Brand brand);
}
