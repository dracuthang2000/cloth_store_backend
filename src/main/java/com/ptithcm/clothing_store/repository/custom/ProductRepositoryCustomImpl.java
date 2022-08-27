package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.model.entity.QProduct;
import com.ptithcm.clothing_store.util.TagUtil;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    @PersistenceContext
    private EntityManager em;


    // get product by tag name
    @Override
    public List<Product> findProductByName(String name) {
        return new JPAQuery<Product>(em)
                .from(QProduct.product)
                .where(QProduct.product.tag.contains(TagUtil.removeAccent(name)))
                .fetch();
    }
}
