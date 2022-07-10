package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.repository.ProductRepository;
import com.ptithcm.clothing_store.service.ProductService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW, readOnly=true, noRollbackFor=Exception.class)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return product;
    }
}
