package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    List<Bill> findAll();
    Optional<Bill> findById(Long id);
}
