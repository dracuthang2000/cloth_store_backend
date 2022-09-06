package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getListDiscount();
    Discount findById(Long id);
    String save(Discount discount);
}
