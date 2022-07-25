package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill/")
public class BillContainer {
    @Autowired
    private BillService billService;

    @GetMapping("/get-list-bill")
    public ResponseEntity<Object> getListBill(){
        return new ResponseEntity<>(billService.findAll(), HttpStatus.OK);
    }
}
