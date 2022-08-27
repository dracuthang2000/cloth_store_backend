package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Material;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.MaterialRepository;
import com.ptithcm.clothing_store.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material findOne(Long id) {
        Material material = materialRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Material can't found");
        });
        return material;
    }

    @Override
    public String save(Material material) {
        try {
            save(material);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("Update or insert is error");
        }
    }
}
