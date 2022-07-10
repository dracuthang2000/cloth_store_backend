package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.service.ProductColorService;
import com.ptithcm.clothing_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/")
public class ProductController extends AbstractApplicationController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductColorService productColorService;

    @GetMapping("get-list-product")
    public ResponseEntity<Object> getAllProduct(){
        return new ResponseEntity<Object>(productService.findAll().stream().map(mapper::productToProductDto), HttpStatus.OK);
    }
    @GetMapping("get-product-by-id")
    public ResponseEntity<Object> getProductById(@RequestParam("id") Long id){
        return new ResponseEntity<Object>(mapper.productToProductDto(productService.findById(id)),HttpStatus.OK);
    }
    @GetMapping("get-product-color-by-color-id")
    public ResponseEntity<Object> getSizeColorById(@RequestParam("id") Long id){
        return new ResponseEntity<Object>(mapper.productColorToProductColorDto(productColorService.findById(id)),HttpStatus.OK);
    }
}
