package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.dto.filter.ConditionSearchListProduct;
import com.ptithcm.clothing_store.model.dto.filter.FilterProduct;
import com.ptithcm.clothing_store.model.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findProductByName(String name);
    List<Product> getTopFiveBestSellerProduct();
    List<Product> findProductByContainTagMaterialAndTagLabelAndTagBrand(FilterProduct filter);
    List<Product> findProductByNewAndName(ConditionSearchListProduct condition);
}
