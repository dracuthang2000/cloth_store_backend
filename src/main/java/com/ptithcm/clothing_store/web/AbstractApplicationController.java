package com.ptithcm.clothing_store.web;

import com.ptithcm.clothing_store.mapper.ApplicationBillMapper;
import com.ptithcm.clothing_store.mapper.ApplicationCustomerMapper;
import com.ptithcm.clothing_store.mapper.ApplicationProductMapper;
import com.ptithcm.clothing_store.mapper.ApplicationUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractApplicationController {
    @Autowired
    ApplicationProductMapper mapper;
    @Autowired
    ApplicationUserMapper userMapper;
    @Autowired
    ApplicationCustomerMapper customerMapper;
    @Autowired
    ApplicationBillMapper billMapper;
}
