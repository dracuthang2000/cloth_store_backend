package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.LabelDto;
import com.ptithcm.clothing_store.model.entity.Label;
import com.ptithcm.clothing_store.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/label/")
@CrossOrigin
public class LabelController extends AbstractApplicationController {
    @Autowired
    LabelService labelService;

    @GetMapping("get-list-label")
    public ResponseEntity<Object> getListLabel(){
        return new ResponseEntity<>(
                labelService.findAll()
                .stream()
                .map(mapper::labelToLabelDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("get-label-by-id")
    public ResponseEntity<Object> getLabelById(@RequestParam("id") Long id){
        return new ResponseEntity<>(
                mapper.labelToLabelDto(labelService.findOne(id)),
                HttpStatus.OK);
    }

    @PostMapping("insert-label")
    public ResponseEntity<Object> insertLabel(@RequestBody LabelDto dto){
        Label entity = mapper.labelDtoToLabel(dto);
        return new ResponseEntity<>(
                labelService.save(entity),HttpStatus.OK
        );
    }

    @PutMapping("update-label")
    public ResponseEntity<Object> updateLabel(@RequestBody LabelDto dto){
        Label entity = mapper.labelDtoToLabel(dto);
        return new ResponseEntity<>(
                labelService.save(entity),HttpStatus.OK
        );
    }

}
