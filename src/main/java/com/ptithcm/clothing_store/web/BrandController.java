package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.mapper.ApplicationProductMapper;
import com.ptithcm.clothing_store.model.dto.BrandDto;
import com.ptithcm.clothing_store.model.dto.Image;
import com.ptithcm.clothing_store.model.entity.Brand;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.service.BrandService;
import com.ptithcm.clothing_store.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController extends AbstractApplicationController {
    private static final String URL_IMG="D:\\CODE\\cloth_store_backend\\src\\main\\resources\\Image\\brand\\";
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
        if(!Objects.isNull(brandDto.getImageByte())) {
            Image image = new Image(brandDto.getImage(), brandDto.getImageByte());
            ImageUtils.downloadImage(image, URL_IMG);
        }
        Brand entity = mapper.brandDtoToBrand(brandDto);
        return new ResponseEntity<>(brandService.save(entity),HttpStatus.OK);
    }

    @PutMapping("update-brand")
    public ResponseEntity<Object> updateBrand(@RequestBody BrandDto brandDto){
        if(!Objects.isNull(brandDto.getImageByte())) {
            Image image = new Image(brandDto.getImage(), brandDto.getImageByte());
            ImageUtils.downloadImage(image, URL_IMG);
        }
        Brand entity = mapper.brandDtoToBrand(brandDto);
        return new ResponseEntity<>(brandService.save(entity),HttpStatus.OK);
    }

    @GetMapping(value = "image/load/{image}")
    public ResponseEntity<Object> loadImageProduct(@PathVariable("image")String image){
        try{
            Path path = Paths.get(URL_IMG + image);
            byte[] bytes = Files.readAllBytes(path);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (IOException e){
            throw new ResourceNotFoundException("Image can't found");
        }
    }
    @PostMapping(value = "image/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile image){
        try{
            byte[] bytes = image.getBytes();
            Path path = Paths.get(URL_IMG+image.getOriginalFilename());
            Files.write(path, bytes);
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        }catch (IOException e){
            throw new ResourceNotFoundException("Image can't found");
        }
    }
}
