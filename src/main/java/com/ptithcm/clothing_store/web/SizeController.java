package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.SizeDto;
import com.ptithcm.clothing_store.model.entity.Size;
import com.ptithcm.clothing_store.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/size/")
public class SizeController extends AbstractApplicationController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("get-list-size")
    public ResponseEntity<Object> getListSize(){
        return new ResponseEntity<>(
                sizeService.findAll().stream()
                        .map(mapper::sizeToSizeDto)
                        .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @GetMapping("get-size-by-id")
    public ResponseEntity<Object> getSizeById(@RequestParam("id")Long id){
        return new ResponseEntity<>(
                mapper.sizeToSizeDto(sizeService.findOne(id)),HttpStatus.OK
        );
    }

    @PostMapping("insert-size")
    public ResponseEntity<Object> insertSize(@RequestBody SizeDto dto){
        Size entity = mapper.sizeDtoToSize(dto);
        return new ResponseEntity<>(
                sizeService.save(entity),HttpStatus.OK
        );
    }

    @PutMapping("update-size")
    public ResponseEntity<Object> updateSize(@RequestBody SizeDto dto){
        Size entity = mapper.sizeDtoToSize(dto);
        return new ResponseEntity<>(
                sizeService.save(entity),HttpStatus.OK
        );
    }
}
