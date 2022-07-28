package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.model.dto.bill.BillUpdateDto;
import com.ptithcm.clothing_store.model.entity.Bill;
import com.ptithcm.clothing_store.model.entity.BillProductDetail;
import com.ptithcm.clothing_store.model.entity.Orders;
import com.ptithcm.clothing_store.model.entity.ProductColorSize;
import com.ptithcm.clothing_store.service.BillService;
import com.ptithcm.clothing_store.service.CustomerService;
import com.ptithcm.clothing_store.service.ProductColorSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill/")
public class BillController extends AbstractApplicationController {
    @Autowired
    private BillService billService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductColorSizeService productColorSizeService;

    @GetMapping("get-list-bill")
    public ResponseEntity<Object> getListBill(){

        return new ResponseEntity<>(billService.findAll()
                .stream()
                .map(billMapper::billToBillDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("get-bill-by-id/{id}")
    public ResponseEntity<Object> getBillById(@PathVariable("id")Long id){
        return new ResponseEntity<>(billMapper.billToBillDto(billService.findById(id)), HttpStatus.OK);
    }


    @GetMapping("get-bill-by-customer-id/{id}")
    public ResponseEntity<Object> getBillByCustomerId(@PathVariable("id")Long id){
        return new ResponseEntity<>(billService.findByCustomerId(id)
                .stream()
                .map(billMapper::billToBillDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("create-bill")
    public ResponseEntity<Object> createBill(@RequestBody BillUpdateDto req){
        Bill entity = billMapper.billDtoToBill(req);
        Orders orders = new Orders();
        req.getBillProductDetails().forEach((res)-> {
                    BillProductDetail detail = new BillProductDetail();
                    ProductColorSize productColorSize = new ProductColorSize();
                    detail = billMapper.billProductDetailDtoToBillProductDetail(res);
                    productColorSize.setId(res.getIdProductColorSizeDto());
                    detail.setProductColorSize(productColorSize);
                    entity.addBillDetail(detail);
                });
        entity.setCustomer(customerService.findCustomerById(req.getIdCustomer()));
        orders.setState(req.getState());
        orders.setStartDate(req.getDate());
        entity.addOrder(orders);
        return new ResponseEntity<>(billService.save(entity),HttpStatus.OK);
    }
}
