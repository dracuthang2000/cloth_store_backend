package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Customer;

public interface CustomerService {
    Customer findPersonByUsername(String username);
    String save(Customer customer);
    Customer findCustomerById(Long id);
}
