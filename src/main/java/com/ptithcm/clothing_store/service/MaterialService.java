package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Material;

import java.util.List;

public interface MaterialService {
    List<Material> findAll();
    Material findOne(Long id);
    String save(Material material);
}
