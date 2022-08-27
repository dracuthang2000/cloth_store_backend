package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.Enum.EnumState;
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
        return CSConstant.SUCCESS;
    }

    @Override
    public List<Bill> findByCustomerId(Long id) {
        List<Bill> bills = billRepository.findByCustomerId(id);
        if(bills.size() == 0){
            throw new ResourceNotFoundException("Can't found bill");
        }
        return bills;
    }

    @Override
    public List<Bill> findByOrdersState(EnumState state) {
        List<Bill> bills = billRepository.findAllByOrdersState(state);
        if(bills.size() == 0){
            throw new ResourceNotFoundException("Can't found bill");
        }
        return bills;
    }
}
