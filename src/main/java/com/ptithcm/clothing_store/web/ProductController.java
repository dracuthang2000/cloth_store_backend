package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.ProductDto;
import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.service.ProductColorService;
import com.ptithcm.clothing_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/")
public class ProductController extends AbstractApplicationController {
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
        return new ResponseEntity<>(mapper.productToProductDto(productService.findByTag(tag)),HttpStatus.OK);
    }
}
