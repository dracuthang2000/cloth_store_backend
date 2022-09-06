package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/discount/")
public class DiscountController extends AbstractApplicationController {
    @Autowired
    DiscountService discountService;

    @GetMapping("get-list-discount")
    public ResponseEntity<Object> getListDiscount(){

        return new ResponseEntity<>(
                discountService.getListDiscount()
                .stream()
                .map(mapper::discountToDiscountDto)
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
