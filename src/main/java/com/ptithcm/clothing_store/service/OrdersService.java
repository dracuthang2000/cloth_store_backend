package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.entity.Orders;

public interface OrdersService {
    String save(Orders orders);
    Orders findOrdersByBillId(Long id);
}
