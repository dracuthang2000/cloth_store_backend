package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.ColorDto;
import com.ptithcm.clothing_store.model.entity.Color;
import com.ptithcm.clothing_store.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/color/")
public class ColorController extends AbstractApplicationController {
    @Autowired
    private ColorService colorService;

    @GetMapping("get-list-color")
    public ResponseEntity<Object> getListColor(){
        return new ResponseEntity<>(
                colorService.findAll().stream()
                .map(mapper::colorToColorDto)
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @GetMapping("get-color-by-id")
    public ResponseEntity<Object> getColorById(@PathVariable("id")Long id){
        return new ResponseEntity<>(
                mapper.colorToColorDto(colorService.findOne(id)),HttpStatus.OK
        );
    }

    @PostMapping("insert-color")
    public ResponseEntity<Object> insertColor(@RequestBody ColorDto dto){
        Color entity = mapper.colorDtoToColor(dto);
        return new ResponseEntity<>(
                colorService.save(entity),HttpStatus.OK
        );
    }

    @PutMapping("update-color")
    public ResponseEntity<Object> updateColor(@RequestBody ColorDto dto){
        Color entity = mapper.colorDtoToColor(dto);
        return new ResponseEntity<>(
                colorService.save(entity),HttpStatus.OK
        );
    }
}
