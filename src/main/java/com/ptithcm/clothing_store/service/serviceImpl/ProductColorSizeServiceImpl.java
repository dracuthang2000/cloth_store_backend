package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.ProductColorSize;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.ProductColorSizeRepository;
import com.ptithcm.clothing_store.service.ProductColorSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductColorSizeServiceImpl implements ProductColorSizeService {
    @Autowired
    ProductColorSizeRepository productColorSizeRepository;
    @Override
    public List<ProductColorSize> findAllById(List<Long> ids) {
        return productColorSizeRepository.findAllById(ids);
    }

    @Override
    public ProductColorSize findById(Long id) {
        ProductColorSize entity = productColorSizeRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Color Size is not existed");
        });
        return entity;
    }
}
