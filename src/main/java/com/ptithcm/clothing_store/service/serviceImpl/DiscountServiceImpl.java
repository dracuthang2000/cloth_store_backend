package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Discount;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.DiscountRepository;
import com.ptithcm.clothing_store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public List<Discount> getListDiscount() {
        return discountRepository.findAll();
    }

    @Override
    public Discount findById(Long id) {
        return discountRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't found discount");
        });
    }

    @Override
    public String save(Discount discount) {
        try {
            discountRepository.save(discount);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("update or insert is error");
        }
    }
}
