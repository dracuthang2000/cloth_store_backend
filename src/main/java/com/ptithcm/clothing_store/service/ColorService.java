package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> findAll();
    Color findOne(Long id);
    String save(Color color);
    Color findColorByName(String color);
}
