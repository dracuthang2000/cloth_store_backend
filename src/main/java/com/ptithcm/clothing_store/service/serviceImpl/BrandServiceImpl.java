package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Brand;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.BrandRepository;
import com.ptithcm.clothing_store.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findOne(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Can't not found brand");
        });
        return brand;
    }

    @Override
    public String save(Brand brand) {
        try{
            brandRepository.save(brand);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("update or insert is error");
        }
    }
}
