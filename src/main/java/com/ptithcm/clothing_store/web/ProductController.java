package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.ProductDto;
import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.model.entity.ProductColor;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.service.ProductColorService;
import com.ptithcm.clothing_store.service.ProductService;
import com.ptithcm.clothing_store.util.TagUtil;
import org.hibernate.engine.jdbc.ReaderInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/")
public class ProductController extends AbstractApplicationController {
    private static final String URL_IMG="D:\\CODE\\cloth_store_backend\\src\\main\\resources\\Image\\product\\";
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductColorService productColorService;

    @GetMapping("get-list-product")
    public ResponseEntity<Object> getAllProduct() {
        return new ResponseEntity<Object>(productService.findAll().stream().map(mapper::productToProductDto), HttpStatus.OK);
    }

    @GetMapping("get-list-product-new")
    public ResponseEntity<Object> getAllProductIsNew() {
        return new ResponseEntity<>(productService.findProductIsNew()
                .stream()
                .map(mapper::productToProductDto)
                , HttpStatus.OK);
    }

    @GetMapping("get-list-product-discount")
    public ResponseEntity<Object> getAllProductDiscount() {
        List<ProductDto> products = productService.findAll()
                .stream()
                .map(mapper::productToProductDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                products
                        .stream()
                        .filter((p) -> Objects.isNull(p.getDiscount()) ? false : true)
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );
    }

    @GetMapping("get-product-by-id")
    public ResponseEntity<Object> getProductById(@RequestParam("id") Long id) {
        return new ResponseEntity<Object>(mapper.productToProductDto(productService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("get-product-color-by-color-id")
    public ResponseEntity<Object> getSizeColorById(@RequestParam("id") Long id) {
        return new ResponseEntity<Object>(mapper.productColorToProductColorDto(productColorService.findById(id)), HttpStatus.OK);
    }
    @GetMapping("get-product-by-tag/{tag}")
    public ResponseEntity<Object> getProductByTag(@PathVariable("tag") String tag){
        return new ResponseEntity<>(mapper.productToProductDto(productService.findByTag(TagUtil.removeDash(tag))),HttpStatus.OK);
    }

    @PostMapping("insert-product")
    public ResponseEntity<Object> insertProduct(@RequestBody ProductDto request){
        request.setId(0l);
        request.setVersion(0l);
        Product entity = mapper.mapperUpdateProduct(request);
        if(!Objects.isNull(request.getColor())&&request.getColor().size()!=0){
            Set<ProductColor> lstProductColor = new HashSet<>();
            request.getColor().stream().forEach((data)->{
                lstProductColor.add(mapper.productColorDtoToProductColor(data));
            });
            entity.setProductColors(lstProductColor);
        }
        return new ResponseEntity<>(productService.save(entity),HttpStatus.OK);
    }

    @PutMapping("update-product")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto request){
        Product entity = mapper.mapperUpdateProduct(request);
        if(!Objects.isNull(request.getColor())&&request.getColor().size()!=0){
            Set<ProductColor> lstProductColor = new HashSet<>();
            request.getColor().stream().forEach((data)->{
                lstProductColor.add(mapper.productColorDtoToProductColor(data));
            });
            entity.setProductColors(lstProductColor);
        }
        return new ResponseEntity<>(productService.save(entity),HttpStatus.OK);
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
    @GetMapping("get-top-five-best-seller-product")
    public ResponseEntity<Object> getTopFiveBestSellerProduct(){
        return new ResponseEntity<>(
                productService.getTopFiveBestSellerProduct()
                .stream()
                .map(mapper::productToProductDto)
                .collect(Collectors.toList()),HttpStatus.OK
        );
    }
}
