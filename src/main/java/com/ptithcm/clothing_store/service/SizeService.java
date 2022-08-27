package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> findAll();
    Size findOne(Long id);
    String save(Size size);
}
