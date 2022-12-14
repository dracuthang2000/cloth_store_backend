package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.dto.filter.ConditionSearchListProduct;
import com.ptithcm.clothing_store.model.dto.filter.FilterProduct;
import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.model.entity.ProductDiscount;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
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
    private static final String SUCCESS = "Success";

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW, readOnly=true, noRollbackFor=Exception.class)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Product is not found");
        });
        return product;
    }

    @Override
    public List<Product> findProductIsNew() {
        List<Product> products = productRepository.findAllByIsNew(true);
        if(products.size()==0){
            throw new ResourceNotFoundException("Not existed product is new");
        }
        return products;
    }

    @Override
    public Product findByTag(String tag) {
        Product product = productRepository.findByTag(tag).orElseThrow(()->{
            throw new ResourceNotFoundException("Product is not found");
        });
        return product;
    }

    @Override
    public List<Product> getTopFiveBestSellerProduct() {
        return productRepository.getTopFiveBestSellerProduct();
    }

    @Override
    public String save(Product product) {
        try {
           productRepository.save(product);
        }catch (Exception e){
            throw new ModifyHandleException("Can't save the product");
        }
        return CSConstant.SUCCESS;
    }

    @Override
    public List<Product> findContainByName(String keyword) {
        return productRepository.findProductByName(keyword);
    }

    @Override
    public List<Product> findProductByContainTagMaterialAndTagLabelAndTagBrand(FilterProduct filter) {
        return productRepository.findProductByContainTagMaterialAndTagLabelAndTagBrand(filter);
    }

    @Override
    public List<Product> findProductByNewAndName(ConditionSearchListProduct condition) {
        return productRepository.findProductByNewAndName(condition);
    }


}
