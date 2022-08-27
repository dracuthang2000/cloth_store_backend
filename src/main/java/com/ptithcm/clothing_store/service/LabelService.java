package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Label;

import java.util.List;

public interface LabelService {
    List<Label> findAll();
    Label findOne(Long id);
    String save(Label label);
}
