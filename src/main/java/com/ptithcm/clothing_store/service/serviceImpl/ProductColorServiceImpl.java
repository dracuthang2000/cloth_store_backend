package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.ProductColor;
import com.ptithcm.clothing_store.repository.ProductColorRepository;
import com.ptithcm.clothing_store.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductColorServiceImpl implements ProductColorService {
    @Autowired
    ProductColorRepository productColorRepository;

    @Override
    public ProductColor findById(Long id) {
        ProductColor productColorSize = productColorRepository.findByIdOrderByProductColorSizeIdAsc(id).orElseThrow();
        return productColorSize;
    }
}
