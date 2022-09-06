package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.dto.Image;
import com.ptithcm.clothing_store.model.dto.ProductDto;
import com.ptithcm.clothing_store.model.dto.filter.ConditionSearchListProduct;
import com.ptithcm.clothing_store.model.dto.filter.FilterProduct;
import com.ptithcm.clothing_store.model.entity.Price;
import com.ptithcm.clothing_store.model.entity.Product;
import com.ptithcm.clothing_store.model.entity.ProductColor;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.service.ProductColorService;
import com.ptithcm.clothing_store.service.ProductService;
import com.ptithcm.clothing_store.util.ImageUtils;
import com.ptithcm.clothing_store.util.TagUtil;
import org.hibernate.engine.jdbc.ReaderInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/")
@CrossOrigin
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
        Product entity = new Product();
        Price price = new Price();
        request.setId(0l);
        request.setVersion(0l);
        if(!Objects.isNull(request.getLstImage())&&request.getLstImage().size()>0){
            request.getLstImage().stream().forEach((data)->ImageUtils.downloadImage(data,URL_IMG));
        }
        entity = mapper.mapperUpdateProduct(request);
        if(!Objects.isNull(request.getColor())&&request.getColor().size()!=0){
            Set<ProductColor> lstProductColor = new HashSet<>();
            request.getColor().stream().forEach((data)->{
                data.setId(0l);
                data.getProductColorSizesDto().stream()
                        .forEach(data1->{
                            data1.setId(0l);
                        });
                lstProductColor.add(mapper.productColorDtoToProductColor(data));
            });
            lstProductColor.stream()
            .forEach((e)->{
                e.addProductColorSize(e.getProductColorSize());
            });
            entity.addProductColor(lstProductColor);
        }
        price.setPrice(entity.getPrice());
        price.setStartDate(LocalDate.now());
        entity.addPriceLog(price);
        productService.save(entity);
        return new ResponseEntity<>(CSConstant.SUCCESS,HttpStatus.OK);
    }

    @PutMapping("update-product")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto request){
        Product entity = mapper.mapperUpdateProduct(request);
        Price price = new Price();
        if(!Objects.isNull(request.getLstImage())&&request.getLstImage().size()>0){
            request.getLstImage().stream().forEach((data)->ImageUtils.downloadImage(data,URL_IMG));
        }
        if(!Objects.isNull(request.getColor())&&request.getColor().size()!=0){
            Set<ProductColor> lstProductColor = new HashSet<>();
            request.getColor().stream().forEach((data)->{
                lstProductColor.add(mapper.productColorDtoToProductColor(data));
            });
            lstProductColor.stream()
                    .forEach((e)->{
                        e.addProductColorSize(e.getProductColorSize());
                    });
            entity.addProductColor(lstProductColor);
        }
        price.setPrice(entity.getPrice());
        price.setStartDate(LocalDate.now());
        entity.addPriceLog(price);
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
    @PostMapping(value = "image/upload")
    public ResponseEntity<Object> uploadImage(@RequestBody Image image){
            ImageUtils.downloadImage(image,URL_IMG);
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);

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

    @GetMapping("find-product-by-name")
    public ResponseEntity<Object> findProductByTagName(@RequestParam("keyword")String keyword){
        return new ResponseEntity<>(
                productService.findContainByName(keyword)
                        .stream()
                        .map(mapper::productToProductDto)
                        .collect(Collectors.toList()),HttpStatus.OK
        );
    }
    @PostMapping("findProductByCondition")
    public ResponseEntity<Object> findProductByContainTagMaterialAndTagLabelAndTagBrand(@RequestBody FilterProduct filter){
        return new ResponseEntity<>(
                productService.findProductByContainTagMaterialAndTagLabelAndTagBrand(filter)
                        .stream()
                        .map(mapper::productToProductDto)
                        .collect(Collectors.toList()),HttpStatus.OK
        );
    }

    @PostMapping("find-product-by-new-and-name")
    public  ResponseEntity<Object> findProductByNewAndName(@RequestBody ConditionSearchListProduct condition){
        return new ResponseEntity<>(
                productService.findProductByNewAndName(condition)
                        .stream()
                        .map(mapper::productToProductDto)
                        .collect(Collectors.toList()),HttpStatus.OK
        );
    }
}
