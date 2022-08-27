package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Label;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.LabelRepository;
import com.ptithcm.clothing_store.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label findOne(Long id) {
        Label label = labelRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Label can't not found");
        });
        return label;
    }

    @Override
    public String save(Label label) {
        try {
            labelRepository.save(label);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("Update or insert is error");
        }
    }
}
