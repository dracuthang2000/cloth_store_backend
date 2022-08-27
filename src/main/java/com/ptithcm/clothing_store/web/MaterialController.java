package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.MaterialDto;
import com.ptithcm.clothing_store.model.entity.Material;
import com.ptithcm.clothing_store.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/material/")
public class MaterialController extends AbstractApplicationController {

    @Autowired
    MaterialService materialService;

    @GetMapping("get-list-material")
    public ResponseEntity<Object> getListMaterial(){
        return new ResponseEntity<>(
                materialService.findAll()
                .stream()
                .map(mapper::materialToMaterialDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("get-material-by-id")
    public ResponseEntity<Object> findMaterialById(@RequestParam("id") Long id){
        return new ResponseEntity<>(
                mapper.materialToMaterialDto(materialService.findOne(id)),HttpStatus.OK);
    }

    @PostMapping("insert-material")
    public ResponseEntity<Object> insertMaterial(@RequestBody MaterialDto dto){
        Material entity = mapper.materialDtoToMaterial(dto);
        return new ResponseEntity<>(materialService.save(entity),HttpStatus.OK);
    }

    @PutMapping("update-material")
    public ResponseEntity<Object> updateMaterial(@RequestBody MaterialDto dto){
        Material entity = mapper.materialDtoToMaterial(dto);
        return new ResponseEntity<>(materialService.save(entity),HttpStatus.OK);
    }
}
