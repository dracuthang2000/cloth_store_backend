package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.entity.Color;
import com.ptithcm.clothing_store.model.exception.ModifyHandleException;
import com.ptithcm.clothing_store.repository.ColorRepository;
import com.ptithcm.clothing_store.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findOne(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(()->{
            throw new ResourceAccessException("Color can't found");
        });
        return color;
    }

    @Override
    public String save(Color color) {
        try{
            colorRepository.save(color);
            return CSConstant.SUCCESS;
        }catch (Exception e){
            throw new ModifyHandleException("Update or insert color is error");
        }
    }
}
