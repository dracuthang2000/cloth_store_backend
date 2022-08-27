package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.mapper.ApplicationProductMapper;
import com.ptithcm.clothing_store.model.dto.BrandDto;
import com.ptithcm.clothing_store.model.entity.Brand;
import com.ptithcm.clothing_store.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
public class BrandController extends AbstractApplicationController {
    @Autowired
    BrandService brandService;

    @GetMapping("get-list-brand")
    public ResponseEntity<Object> getListBrand(){
        return new ResponseEntity<>(
                brandService.findAll()
                .stream()
                .map(mapper::brandToBrandDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> getBrandById(@RequestParam("id") Long id){
        return new ResponseEntity<>(
                mapper.brandToBrandDto(brandService.findOne(id)),HttpStatus.OK);
    }

    @PostMapping("insert-brand")
    public ResponseEntity<Object> insertBrand(@RequestBody BrandDto brandDto){
        Brand entity = mapper.brandDtoToBrand(brandDto);
        return new ResponseEntity<>(brandService.save(entity),HttpStatus.OK);
    }

    @PutMapping("update-brand")
    public ResponseEntity<Object> updateBrand(@RequestBody BrandDto brandDto){
        Brand entity = mapper.brandDtoToBrand(brandDto);
        return new ResponseEntity<>(brandService.save(entity),HttpStatus.OK);
    }
}
