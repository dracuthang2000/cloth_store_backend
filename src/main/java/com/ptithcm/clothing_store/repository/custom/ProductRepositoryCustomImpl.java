package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.dto.filter.ConditionSearchListProduct;
import com.ptithcm.clothing_store.model.dto.filter.FilterProduct;
import com.ptithcm.clothing_store.model.entity.*;
import com.ptithcm.clothing_store.util.TagUtil;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                .fetch();
    }

    @Override
    public List<Product> getTopFiveBestSellerProduct() {
        Query query = em.createQuery("SELECT P.id FROM BillProductDetail BPD " +
                "INNER JOIN ProductColorSize PCZ ON PCZ.id = BPD.productColorSize.id " +
                "INNER JOIN ProductColor PC ON PC.id = PCZ.productColor.id " +
                "INNER JOIN Product P ON PC.product.id = P.id " +
                "GROUP BY P.id ORDER BY SUM(BPD.quantity) DESC",Long.class);
        List<Long> lstId = query.setMaxResults(5).getResultList();
        return new JPAQuery<Product>(em)
                .select(QProduct.product)
                .from(QProduct.product)
                .where(QProduct.product.id.in(lstId))
                .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                .fetch();
    }

    @Override
    public List<Product> findProductByContainTagMaterialAndTagLabelAndTagBrand(FilterProduct filter) {
        return new JPAQuery<Product>(em)
                .from(QProduct.product)
                .where(QProduct.product.brand.tag.contains(TagUtil.removeDash(filter.getTagBrand()))
                        .and(QProduct.product.label.tag.contains(TagUtil.removeDash(filter.getTagLabel())))
                        .and(QProduct.product.material.tag.contains(TagUtil.removeDash(filter.getTagMaterial()))))
                .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                .fetch();
    }

    @Override
    public List<Product> findProductByNewAndName(ConditionSearchListProduct condition) {
        if(condition.getIsNew()==null){
            return new JPAQuery<Product>(em)
                    .from(QProduct.product)
                    .where(QProduct.product.tag.contains(TagUtil.removeAccent(condition.getKeyWord())))
                    .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                    .fetch();
        }else if(condition.getIsNew()!=null && Objects.isNull(condition.getKeyWord())){
            return new JPAQuery<Product>(em)
                    .from(QProduct.product)
                    .where(QProduct.product.isNew.eq(condition.getIsNew()))
                    .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                    .fetch();
        }else if(condition.getIsNew()!=null && !Objects.isNull(condition.getKeyWord())){
            return new JPAQuery<Product>(em)
                    .from(QProduct.product)
                    .where(QProduct.product.tag.contains(TagUtil.removeAccent(condition.getKeyWord())).and(QProduct.product.isNew.eq(condition.getIsNew())))
                    .setHint("javax.persistence.fetchgraph",em.getEntityGraph("graph-product"))
                    .fetch();
        }
        return null;
    }
}
