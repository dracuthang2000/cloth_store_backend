package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Bill;
import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill findById(Long id);
    String save(Bill bill);
}
