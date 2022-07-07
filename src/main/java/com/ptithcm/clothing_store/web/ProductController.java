package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.service.ProductService;
import com.ptithcm.clothing_store.util.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/")
public class ProductController extends AbstractApplicationController {
    @Autowired
    private ProductService productService;

    @GetMapping("get-list-product")
    public ResponseEntity<Object> getAllProduct(){
        return new ResponseEntity<Object>(productService.findAll().stream().map(mapper::productToProductDto), HttpStatus.OK);
    }
}
