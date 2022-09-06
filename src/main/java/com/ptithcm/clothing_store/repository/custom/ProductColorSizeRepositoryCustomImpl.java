package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.entity.ProductColorSize;
import com.ptithcm.clothing_store.model.entity.QProductColorSize;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductColorSizeRepositoryCustomImpl implements ProductColorSizeRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer getQuantityInStock(Long id) {
        return new JPAQuery<Integer>(em)
                .select(QProductColorSize.productColorSize.quantity)
                .from(QProductColorSize.productColorSize)
                .where(QProductColorSize.productColorSize.id.eq(id))
                .fetchOne();
    }
}
