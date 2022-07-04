package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.entity.Customer;

public interface CustomerRepositoryCustom {
    Customer findCustomerByUsername(String username);
}
