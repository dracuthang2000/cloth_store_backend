package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Bill;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.BillRepository;
import com.ptithcm.clothing_store.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    public static final String SUCCESS = "SUCCESS";
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Bill can't found");
        });
    }

    @Override
    public String save(Bill bill) {
        billRepository.save(bill);
        return SUCCESS;
    }
}
