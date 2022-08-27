package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Size;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.SizeRepository;
import com.ptithcm.clothing_store.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findOne(Long id) {
        Size entity = sizeRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Size can't found");
        });
        return entity;
    }

    @Override
    public String save(Size size) {
        try {
            sizeRepository.save(size);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("Update or insert size is error");
        }
    }
}
