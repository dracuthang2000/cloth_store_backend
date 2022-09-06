package com.ptithcm.clothing_store;

import com.ptithcm.clothing_store.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Test
    void getCustomer(){
        customerService.findPersonByUsername("user1");
    }
}
